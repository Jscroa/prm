package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;

public interface IAccToGroupDao extends IBaseDao<AccToGroup> {
	
	public List<AccGroup> getGroups(String accId);
	
	public List<Account> getAccounts(String groupId);
	
	public AccToGroup get(String accId,String groupId);
}
