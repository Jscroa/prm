package cn.prm.server.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.IAccGroupDao;
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.AccGroupForm;

/**
 * @Title: AccGroupService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:15<br>
 * @version v1.0<br>
 */
@Service(value = "accGroupService")
public class AccGroupService {

    private static final Logger log = LoggerFactory.getLogger(AccGroupService.class);

    @Autowired
    IAccGroupDao                accGroupDao;
    @Autowired
    IAccToGroupDao              accToGroupDao;

    /**
     * @Title: createGroup<br>
     * @Description: <br>
     * @param currUser
     * @param form
     * @throws BusinessException
     */
    public void createGroup(CurrUser currUser, AccGroupForm form) throws BusinessException {
        String groupName = form.getGroupName();
        if (groupName == null || "".equals(groupName)) {
            throw new BusinessException("组名不能为空");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AccGroup accGroup = new AccGroup();
        accGroup.setGuid(UUIDUtil.randomUUID());
        accGroup.setIsPrivate(false);
        accGroup.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        accGroup.setStdName(form.getGroupName());
        accGroup.setCreateTime(now);
        accGroup.setModifyTime(now);
        accGroup.setCreateUser(currUser.getGuid());
        accGroup.setModifyUser(currUser.getGuid());
        accGroupDao.add(accGroup);

        AccToGroup accToGroup = new AccToGroup();
        accToGroup.setGuid(UUIDUtil.randomUUID());
        accToGroup.setAccId(currUser.getGuid());
        accToGroup.setGroupId(accGroup.getGuid());
        accToGroup.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        accToGroup.setCreateTime(now);
        accToGroup.setModifyTime(now);
        accToGroup.setCreateUser(currUser.getGuid());
        accToGroup.setModifyUser(currUser.getGuid());
        accToGroupDao.add(accToGroup);
    }

    /**
     * @Title: addAcc<br>
     * @Description: <br>
     * @param currUser
     * @param groupId
     * @param accIds
     * @throws BusinessException
     */
    public void addAcc(CurrUser currUser, String groupId, List<String> accIds) throws BusinessException {
        if (groupId == null || "".equals(groupId)) {
            throw new BusinessException("未指定账户组");
        }
        if (accIds == null || accIds.size() == 0) {
            return;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < accIds.size(); i++) {
            AccToGroup accToGroup = new AccToGroup();
            accToGroup.setGuid(UUIDUtil.randomUUID());
            accToGroup.setAccId(accIds.get(i));
            accToGroup.setGroupId(groupId);
            accToGroup.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
            accToGroup.setCreateTime(now);
            accToGroup.setModifyTime(now);
            accToGroup.setCreateUser(currUser.getGuid());
            accToGroup.setModifyUser(currUser.getGuid());
            accToGroupDao.add(accToGroup);
        }
    }

    /**
     * @Title: getGroupList<br>
     * @Description: <br>
     * @param currUser
     * @return
     */
    public List<AccGroup> getGroupList(CurrUser currUser) {
        List<AccGroup> list = accToGroupDao.getGroups(currUser.getGuid());
        return list;
    }

    /**
     * @Title: getAccountList<br>
     * @Description: <br>
     * @param currUser
     * @param groupId
     * @return
     */
    public List<Account> getAccountList(CurrUser currUser, String groupId) {
        AccToGroup accToGroup = accToGroupDao.get(currUser.getGuid(), groupId);
        if (accToGroup == null) {
            log.info("没权限访问账户组(" + groupId + "),返回空集");
            return new ArrayList<>();
        }
        List<Account> list = accToGroupDao.getAccounts(groupId);
        return list;
    }
}
