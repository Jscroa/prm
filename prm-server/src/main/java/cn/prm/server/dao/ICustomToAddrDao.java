package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Address;
import cn.prm.server.entity.CustomToAddr;

public interface ICustomToAddrDao extends IBaseDao<CustomToAddr> {
	
	public List<Address> getAddresses(String customId);
	
}
