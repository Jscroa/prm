package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Account;

public interface IAccountDao extends IBaseDao<Account>{
	public List<Account> find(String email,String pwd);
	public List<Account> findByEmail(String email);
	public List<Account> findByPhone(String phone);
}
