package cn.prm.server.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.IAccGroupDao;
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.dao.IAccountDao;
import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.UserLoginForm;
import cn.prm.server.form.UserRegisterForm;

/**
 * @Title: UserService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:55<br>
 * @version v1.0<br>
 */
@Service(value = "userService")
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    IAccountDao                 accountDao;
    @Autowired
    IAccGroupDao                accGroupDao;
    @Autowired
    IAccToGroupDao              accToGroupDao;

    /**
     * @Title: login<br>
     * @Description: <br>
     * @param form
     * @return
     * @throws BusinessException
     */
    public CurrUser login(UserLoginForm form) throws BusinessException {
        List<Account> list = accountDao.find(form.getEmail(), form.getPassword());

        if (list == null || list.size() == 0) {
            throw new BusinessException("用户名或密码错误");
        }

        if (list.size() > 1) {
            throw new BusinessException("服务器数据发生错误");
        }
        Account account = list.get(0);
        CurrUser user = new CurrUser();
        user.setGuid(account.getGuid());
        user.setName(account.getStdName());
        return user;
    }

    /**
     * @Title: register<br>
     * @Description: <br>
     * @param form
     * @return
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public CurrUser register(UserRegisterForm form) throws BusinessException {
        List<Account> list1 = accountDao.findByEmail(form.getEmail());
        if (list1 != null && list1.size() > 0) {
            throw new BusinessException("该邮箱已注册");
        }

        List<Account> list2 = accountDao.findByPhone(form.getPhone());
        if (list2 != null && list2.size() > 0) {
            throw new BusinessException("该手机号已注册");
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Account account = new Account();
        account.setGuid(UUIDUtil.randomUUID());
        account.setEmail(form.getEmail());
        account.setStdName(form.getUserName());
        account.setPwd(form.getPassword());
        account.setPhone(form.getPhone());
        account.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        account.setCreateTime(now);
        account.setModifyTime(now);
        accountDao.add(account);

        AccGroup accGroup = new AccGroup();
        accGroup.setGuid(UUIDUtil.randomUUID());
        accGroup.setIsPrivate(true);
        accGroup.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        accGroup.setCreateTime(now);
        accGroup.setModifyTime(now);
        accGroupDao.add(accGroup);

        AccToGroup accToGroup = new AccToGroup();
        accToGroup.setGuid(UUIDUtil.randomUUID());
        accToGroup.setAccId(account.getGuid());
        accToGroup.setGroupId(accGroup.getGuid());
        accToGroup.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        accToGroup.setCreateTime(now);
        accToGroup.setModifyTime(now);
        accToGroupDao.add(accToGroup);

        CurrUser user = new CurrUser();
        user.setGuid(account.getGuid());
        user.setName(account.getStdName());
        log.info("注册 -> " + account.getStdName());
        return user;
    }

}
