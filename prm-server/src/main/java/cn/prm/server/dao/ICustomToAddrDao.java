package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Address;
import cn.prm.server.entity.CustomToAddr;

/**
 * @Title: ICustomToAddrDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:31:45
 * @version v1.0
 */
public interface ICustomToAddrDao extends IBaseDao<CustomToAddr> {
	
	/** 
	 * @Title: getAddresses 
	 * @Description: 
	 * @param customId
	 * @return
	 * @throws 
	 */
	public List<Address> getAddresses(String customId);
	
}
