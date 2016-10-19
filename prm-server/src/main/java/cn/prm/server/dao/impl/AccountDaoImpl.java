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
import cn.prm.server.dao.IAccountDao;
import cn.prm.server.entity.Account;

/**
 * @Title: AccountDaoImpl.java
 * @Package: cn.prm.server.dao.impl
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:29:22
 * @version v1.0
 */
@Repository
public class AccountDaoImpl implements IAccountDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,acc,pwd,phone,email,sex,age";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Account extract(ResultSet rs) throws SQLException, DataAccessException {
		Account account = new Account();
		account.setGuid(rs.getString("guid"));
		account.setStdName(rs.getString("std_name"));
		account.setStdCode(rs.getInt("std_code"));
		account.setStatus(rs.getInt("status"));
		account.setMemo(rs.getString("memo"));
		account.setCreateUser(rs.getString("create_user"));
		account.setModifyUser(rs.getString("modify_user"));
		account.setCreateTime(rs.getTimestamp("create_time"));
		account.setModifyTime(rs.getTimestamp("modify_time"));
		account.setAcc(rs.getString("acc"));
		account.setPwd(rs.getString("pwd"));
		account.setPhone(rs.getString("phone"));
		account.setEmail(rs.getString("email"));
		account.setSex(rs.getBoolean("sex"));
		account.setAge(rs.getInt("age"));
		return account;
	}

	@Override
	public void add(final Account t) {
		String sql = "insert into t_account(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setString(10, t.getAcc());
				ps.setString(11, t.getPwd());
				ps.setString(12, t.getPhone());
				ps.setString(13, t.getEmail());
				ps.setObject(14, t.getSex());
				ps.setObject(15, t.getAge());
			}
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_account where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final Account t) {
		String sql = "update t_account set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,acc=?,pwd=?,phone=?,email=?,sex=?,age=? where guid=?";
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
				ps.setString(9, t.getAcc());
				ps.setString(10, t.getPwd());
				ps.setString(11, t.getPhone());
				ps.setString(12, t.getEmail());
				ps.setObject(13, t.getSex());
				ps.setObject(14, t.getAge());
				ps.setString(15, t.getGuid());
			}
		});
	}

	@Override
	public Account get(String id) {
		String sql = "select " + COLS + " from t_account where guid=?";

		List<Account> list = jdbcTemplate.query(sql, new Object[] { id, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<Account>() {

					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Account> find(String email, String pwd) {
		String sql = "select " + COLS + " from t_account where email=? and pwd=? and status=?";

		List<Account> list = jdbcTemplate.query(sql, new Object[] { email, pwd, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<Account>() {

					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		return list;
	}

	@Override
	public List<Account> findByEmail(String email) {
		String sql = "select " + COLS + " from t_account where email=? and status=?";
		List<Account> list = jdbcTemplate.query(sql, new Object[] { email, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<Account>() {

					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		return list;
	}

	@Override
	public List<Account> findByPhone(String phone) {
		String sql = "select " + COLS + " from t_account where phone=? and status=?";
		List<Account> list = jdbcTemplate.query(sql, new Object[] { phone, Constants.DB_STATUS.STATUS_ACTIVE },
				new RowMapper<Account>() {

					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		return list;
	}

}
