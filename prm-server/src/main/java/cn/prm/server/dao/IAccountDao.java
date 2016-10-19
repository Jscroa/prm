package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Account;

/**
 * @Title: IAccountDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:30:17
 * @version v1.0
 */
public interface IAccountDao extends IBaseDao<Account> {
	
	/** 
	 * @Title: find 
	 * @Description: 
	 * @param email
	 * @param pwd
	 * @return
	 * @throws 
	 */
	public List<Account> find(String email, String pwd);

	/** 
	 * @Title: findByEmail 
	 * @Description: 
	 * @param email
	 * @return
	 * @throws 
	 */
	public List<Account> findByEmail(String email);

	/** 
	 * @Title: findByPhone 
	 * @Description: 
	 * @param phone
	 * @return
	 * @throws 
	 */
	public List<Account> findByPhone(String phone);
}
