package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;

/**
 * @Title: IAccToGroupDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:30:44<br>
 * @version v1.0<br>
 */
public interface IAccToGroupDao extends IBaseDao<AccToGroup> {

    /**
     * @Title: getGroups<br>
     * @Description: <br>
     * @param accId
     * @return
     */
    List<AccGroup> getGroups(String accId);

    /**
     * @Title: getAccounts<br>
     * @Description: <br>
     * @param groupId
     * @return
     */
    List<Account> getAccounts(String groupId);

    /**
     * @Title: get<br>
     * @Description: <br>
     * @param accId
     * @param groupId
     * @return
     */
    AccToGroup get(String accId, String groupId);
}
