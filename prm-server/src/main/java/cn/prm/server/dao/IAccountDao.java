package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Account;

/**
 * @Title: IAccountDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:30:17<br>
 * @version v1.0<br>
 */
public interface IAccountDao extends IBaseDao<Account> {

    /**
     * @Title: find<br>
     * @Description: <br>
     * @param email
     * @param pwd
     * @return
     */
    public List<Account> find(String email, String pwd);

    /**
     * @Title: findByEmail<br>
     * @Description: <br>
     * @param email
     * @return
     */
    public List<Account> findByEmail(String email);

    /**
     * @Title: findByPhone<br>
     * @Description: <br>
     * @param phone
     * @return
     */
    public List<Account> findByPhone(String phone);
}
