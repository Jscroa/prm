package cn.prm.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

/**
 * @Title: IBaseDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:31:08
 * @version v1.0
 * @param <T>
 */
public interface IBaseDao<T> {

	/** 
	 * @Title: extract 
	 * @Description: 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws DataAccessException
	 * @throws 
	 */
	public T extract(ResultSet rs) throws SQLException, DataAccessException;

	/** 
	 * @Title: add 
	 * @Description: 
	 * @param t
	 * @throws 
	 */
	public void add(T t);

	/** 
	 * @Title: delete 
	 * @Description: 
	 * @param id
	 * @throws 
	 */
	public void delete(String id);

	/** 
	 * @Title: modify 
	 * @Description: 
	 * @param t
	 * @throws 
	 */
	public void modify(T t);

	/** 
	 * @Title: get 
	 * @Description: 
	 * @param id
	 * @return
	 * @throws 
	 */
	public T get(String id);

}
