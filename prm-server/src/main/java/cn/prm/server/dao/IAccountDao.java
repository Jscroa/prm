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
     * @Description: 根据email和密码获取账户信息，登录时候用<br>
     * @param email
     * @param pwd
     * @return
     */
    List<Account> find(String email, String pwd);

    /**
     * @Title: findByEmail<br>
     * @Description: 根据email获取账户信息<br>
     * @param email
     * @return
     */
    List<Account> findByEmail(String email);

    /**
     * @Title: findByPhone<br>
     * @Description: 根据手机号获取账户信息<br>
     * @param phone
     * @return
     */
    List<Account> findByPhone(String phone);
}
