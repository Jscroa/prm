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

import cn.prm.server.commons.Constants;
import cn.prm.server.dao.IAccGroupDao;
import cn.prm.server.entity.AccGroup;

@Repository
public class AccGroupDaoImpl implements IAccGroupDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,is_private";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AccGroup extract(ResultSet rs) throws SQLException, DataAccessException {
		AccGroup accGroup = new AccGroup();
		accGroup.setGuid(rs.getString("guid"));
		accGroup.setStdName(rs.getString("std_name"));
		accGroup.setStdCode(rs.getInt("std_code"));
		accGroup.setStatus(rs.getInt("status"));
		accGroup.setMemo(rs.getString("memo"));
		accGroup.setCreateUser(rs.getString("create_user"));
		accGroup.setModifyUser(rs.getString("modify_user"));
		accGroup.setCreateTime(rs.getTimestamp("create_time"));
		accGroup.setModifyTime(rs.getTimestamp("modify_time"));
		accGroup.setIsPrivate(rs.getBoolean("is_private"));
		return accGroup;
	}

	@Override
	public void add(final AccGroup t) {
		String sql = "insert into t_acc_group(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

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
				ps.setBoolean(10, t.getIsPrivate());
			}
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_acc_group where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final AccGroup t) {
		String sql = "update t_acc_group set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,is_private=? where guid=?";
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
				ps.setBoolean(9, t.getIsPrivate());
				ps.setString(10, t.getGuid());
			}
		});
	}

	@Override
	public AccGroup get(String id) {
		String sql = "select " + COLS + " from t_acc_group where guid=?";

		List<AccGroup> list = jdbcTemplate.query(sql, new Object[] { id, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<AccGroup>() {

					@Override
					public AccGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
