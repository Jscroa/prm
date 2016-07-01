package cn.prm.server.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.prm.server.dao.IBaseDao;

public abstract class BaseDaoImpl implements IBaseDao {
	JdbcTemplate jdbcTemplate;
	
	public <T> void add(T t){
		
	}
}
