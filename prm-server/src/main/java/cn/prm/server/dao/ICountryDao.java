package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Country;

/**
 * @Title: ICountryDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:31:54
 * @version v1.0
 */
public interface ICountryDao extends IBaseDao<Country> {

	/** 
	 * @Title: getByStatus 
	 * @Description: 
	 * @param status
	 * @param order
	 * @return
	 * @throws 
	 */
	List<Country> getByStatus(int status,String order);
}
