package cn.prm.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

public interface IBaseDao<T> {

	public T extract(ResultSet rs) throws SQLException, DataAccessException;

	public void add(T t);

	public void delete(String id);

	public void modify(T t);

	public T get(String id);

}
