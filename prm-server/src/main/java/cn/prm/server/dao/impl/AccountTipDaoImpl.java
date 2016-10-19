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
import cn.prm.server.dao.IAccountTipDao;
import cn.prm.server.entity.Account;
import cn.prm.server.entity.AccountTip;

@Repository
public class AccountTipDaoImpl implements IAccountTipDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public AccountTip extract(ResultSet rs) throws SQLException, DataAccessException {
		AccountTip accountTip = new AccountTip();
		accountTip.setGuid(rs.getString("guid"));
		accountTip.setStdName(rs.getString("std_name"));
		accountTip.setStdCode(rs.getInt("std_code"));
		accountTip.setStatus(rs.getInt("status"));
		accountTip.setMemo(rs.getString("memo"));
		accountTip.setCreateUser(rs.getString("create_user"));
		accountTip.setModifyUser(rs.getString("modify_user"));
		accountTip.setCreateTime(rs.getTimestamp("create_time"));
		accountTip.setModifyTime(rs.getTimestamp("modify_time"));
		return accountTip;
	}

	@Override
	public void add(final AccountTip t) {
		String sql = "insert into t_acc_tip(" + COLS + ") values(?,?,?,?,?,?,?,?,?)";
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
			}
			
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_acc_tip where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final AccountTip t) {
		String sql = "update t_acc_tip set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=? where guid=?";
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
				ps.setString(9, t.getGuid());
			}
		});
	}

	@Override
	public AccountTip get(String id) {
		String sql = "select " + COLS + " from t_acc_tip where guid=?";

		List<AccountTip> list = jdbcTemplate.query(sql, new Object[] { id, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<AccountTip>() {

					@Override
					public AccountTip mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
