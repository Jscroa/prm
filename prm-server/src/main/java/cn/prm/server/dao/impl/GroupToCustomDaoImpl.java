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
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.IGroupToCustomDao;
import cn.prm.server.entity.Contact;
import cn.prm.server.entity.Custom;
import cn.prm.server.entity.CustomToContact;
import cn.prm.server.entity.GroupToCustom;

@Repository
public class GroupToCustomDaoImpl implements IGroupToCustomDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,group_id,custom_id";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ICustomDao customDao;
	
	@Override
	public GroupToCustom extract(ResultSet rs) throws SQLException, DataAccessException {
		GroupToCustom groupToCustom = new GroupToCustom();
		groupToCustom.setStdName(rs.getString("std_name"));
		groupToCustom.setStdCode(rs.getInt("std_code"));
		groupToCustom.setStatus(rs.getInt("status"));
		groupToCustom.setMemo(rs.getString("memo"));
		groupToCustom.setCreateUser(rs.getString("create_user"));
		groupToCustom.setModifyUser(rs.getString("modify_user"));
		groupToCustom.setCreateTime(rs.getTimestamp("create_time"));
		groupToCustom.setModifyTime(rs.getTimestamp("modify_time"));
		groupToCustom.setGroupId(rs.getString("group_id"));
		groupToCustom.setCustomId(rs.getString("custom_id"));
		return groupToCustom;
	}

	@Override
	public void add(final GroupToCustom t) {
		String sql = "insert into t_group_to_custom(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setString(10, t.getGroupId());
				ps.setString(11, t.getCustomId());
			}
			
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_group_to_custom where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final GroupToCustom t) {
		String sql = "update t_group_to_custom set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,group_id=?,custom_id=? where guid=?";
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
				ps.setString(9, t.getGroupId());
				ps.setString(10, t.getCustomId());
				ps.setString(11, t.getGuid());
			}
		});
	}

	@Override
	public GroupToCustom get(String id) {
		String sql = "select " + COLS + " from t_group_to_custom where guid=?";
		List<GroupToCustom> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<GroupToCustom>() {

					@Override
					public GroupToCustom mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Custom> getCustoms(String groupId) {
		String sql = "select t2.* from t_group_to_custom t1 left join t_custom t2 on t2.guid=t1.custom_id where t1.group_id=? and t1.status=? and t2.status=?";
		List<Custom> list = jdbcTemplate.query(sql, new Object[]{groupId,DB_STATUS.STATUS_ACTIVE,DB_STATUS.STATUS_ACTIVE},new RowMapper<Custom>(){

			@Override
			public Custom mapRow(ResultSet rs, int rowNum) throws SQLException {
				return customDao.extract(rs);
			}
			
		});
		return list;
	}

}
