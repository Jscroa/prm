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

import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.dao.IAccGroupDao;
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.dao.IAccountDao;
import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.AccToGroup;
import cn.prm.server.entity.Account;

@Repository
public class AccToGroupDaoImpl implements IAccToGroupDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,acc_id,group_id";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	IAccGroupDao accGroupDao;
	
	@Autowired
	IAccountDao accountDao;

	@Override
	public AccToGroup extract(ResultSet rs) throws SQLException, DataAccessException {
		AccToGroup accToGroup = new AccToGroup();
		accToGroup.setGuid(rs.getString("guid"));
		accToGroup.setStdName(rs.getString("std_name"));
		accToGroup.setStdCode(rs.getInt("std_code"));
		accToGroup.setStatus(rs.getInt("status"));
		accToGroup.setMemo(rs.getString("memo"));
		accToGroup.setCreateUser(rs.getString("create_user"));
		accToGroup.setModifyUser(rs.getString("modify_user"));
		accToGroup.setCreateTime(rs.getTimestamp("create_time"));
		accToGroup.setModifyTime(rs.getTimestamp("modify_time"));
		accToGroup.setAccId(rs.getString("acc_id"));
		accToGroup.setGroupId(rs.getString("group_id"));
		return accToGroup;
	}

	@Override
	public void add(final AccToGroup t) {
		String sql = "insert into t_acc_to_group(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setString(10, t.getAccId());
				ps.setString(11, t.getGroupId());
			}
			
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_acc_to_group where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final AccToGroup t) {
		String sql = "update t_acc_to_group set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,acc_id=?,group_id=? where guid=?";
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
				ps.setString(9, t.getAccId());
				ps.setString(10, t.getGroupId());
				ps.setString(11, t.getGuid());
			}
		});
	}

	@Override
	public AccToGroup get(String id) {
		String sql = "select " + COLS + " from t_acc_to_group where guid=?";
		List<AccToGroup> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<AccToGroup>() {

					@Override
					public AccToGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AccGroup> getGroups(String accId){
		String sql = "select t2.* from t_acc_to_group t1 left join t_acc_group t2 on t2.guid=t1.group_id where t1.acc_id=? and t1.status=? and t2.status=?";
		List<AccGroup> list = jdbcTemplate.query(sql, new Object[]{accId,DB_STATUS.STATUS_ACTIVE,DB_STATUS.STATUS_ACTIVE},new RowMapper<AccGroup>(){

			@Override
			public AccGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				return accGroupDao.extract(rs);
			}
			
		});
		return list;
	}
	
	@Override
	public List<Account> getAccounts(String groupId) {
		String sql = "select t2.* from t_acc_to_group t1 left join t_account t2 on t2.guid=t2.acc_id where t1.group_id=? and t1.status=? and t2.status=?";
		List<Account> list = jdbcTemplate.query(sql,new Object[]{groupId,DB_STATUS.STATUS_ACTIVE,DB_STATUS.STATUS_ACTIVE}, new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				return accountDao.extract(rs);
			}
			
		});
		return list;
	}
	
	@Override
	public AccToGroup get(String accId, String groupId) {
		String sql = "select "+COLS+" from t_acc_to_group where acc_id=? and group_id=? and status=?";
		List<AccToGroup> list = jdbcTemplate.query(sql, new Object[] { accId,groupId,DB_STATUS.STATUS_ACTIVE },
				new RowMapper<AccToGroup>() {

					@Override
					public AccToGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
}
