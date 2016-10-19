package cn.prm.server.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.prm.server.dao.ICustomDao;
import cn.prm.server.entity.Custom;

/**
 * @Title: CustomDaoImpl.java
 * @Package: cn.prm.server.dao.impl
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:29:43
 * @version v1.0
 */
@Repository
public class CustomDaoImpl implements ICustomDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,sex,birthday";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Custom extract(ResultSet rs) throws SQLException, DataAccessException {
		Custom custom = new Custom();
		custom.setGuid(rs.getString("guid"));
		custom.setStdName(rs.getString("std_name"));
		custom.setStdCode(rs.getInt("std_code"));
		custom.setStatus(rs.getInt("status"));
		custom.setMemo(rs.getString("memo"));
		custom.setCreateUser(rs.getString("create_user"));
		custom.setModifyUser(rs.getString("modify_user"));
		custom.setCreateTime(rs.getTimestamp("create_time"));
		custom.setModifyTime(rs.getTimestamp("modify_time"));
		custom.setSex(rs.getBoolean("sex"));
		custom.setBirthday(rs.getDate("birthday"));
		return custom;
	}

	@Override
	public void add(final Custom t) {
		String sql = "insert into t_custom(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, t.getGuid());
				ps.setString(2, t.getStdName());
				ps.setObject(3, t.getStdCode());
				ps.setObject(4, t.getStatus());
				ps.setString(5, t.getMemo());
				ps.setString(6, t.getCreateUser());
				ps.setString(7, t.getModifyUser());
				ps.setTimestamp(8, t.getCreateTime());
				ps.setTimestamp(9, t.getModifyTime());
				ps.setBoolean(10, t.getSex());
				ps.setDate(11, t.getBirthday());
			}
			
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_custom where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final Custom t) {
		String sql = "update t_custom set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,sex=?,birthday=? where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, t.getStdName());
				ps.setObject(2, t.getStdCode());
				ps.setObject(3, t.getStatus());
				ps.setString(4, t.getMemo());
				ps.setString(5, t.getCreateUser());
				ps.setString(6, t.getModifyUser());
				ps.setTimestamp(7, t.getCreateTime());
				ps.setTimestamp(8, t.getModifyTime());
				ps.setBoolean(9, t.getSex());
				ps.setDate(10, t.getBirthday());
				ps.setString(11, t.getGuid());
			}
		});
	}

	@Override
	public Custom get(String id) {
		String sql = "select " + COLS + " from t_custom where guid=?";
		List<Custom> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Custom>() {

			@Override
			public Custom mapRow(ResultSet rs, int rowNum) throws SQLException {
				return extract(rs);
			}
		});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
