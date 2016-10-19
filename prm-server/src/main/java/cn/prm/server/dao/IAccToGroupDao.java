package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;

/**
 * @Title: IAccToGroupDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:30:44
 * @version v1.0
 */
public interface IAccToGroupDao extends IBaseDao<AccToGroup> {
	
	/** 
	 * @Title: getGroups 
	 * @Description: 
	 * @param accId
	 * @return
	 * @throws 
	 */
	public List<AccGroup> getGroups(String accId);
	
	/** 
	 * @Title: getAccounts 
	 * @Description: 
	 * @param groupId
	 * @return
	 * @throws 
	 */
	public List<Account> getAccounts(String groupId);
	
	/** 
	 * @Title: get 
	 * @Description: 
	 * @param accId
	 * @param groupId
	 * @return
	 * @throws 
	 */
	public AccToGroup get(String accId,String groupId);
}
