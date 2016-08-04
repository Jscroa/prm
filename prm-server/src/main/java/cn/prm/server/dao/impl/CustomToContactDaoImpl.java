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
import cn.prm.server.dao.IContactDao;
import cn.prm.server.dao.ICustomToContactDao;
import cn.prm.server.entity.Contact;
import cn.prm.server.entity.CustomToContact;

@Repository
public class CustomToContactDaoImpl implements ICustomToContactDao {

	private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,custom_id,contact_id";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	IContactDao contactDao;
	
	@Override
	public CustomToContact extract(ResultSet rs) throws SQLException, DataAccessException {
		CustomToContact customToContact = new CustomToContact();
		customToContact.setGuid(rs.getString("guid"));
		customToContact.setStdName(rs.getString("std_name"));
		customToContact.setStdCode(rs.getInt("std_code"));
		customToContact.setStatus(rs.getInt("status"));
		customToContact.setMemo(rs.getString("memo"));
		customToContact.setCreateUser(rs.getString("create_user"));
		customToContact.setModifyUser(rs.getString("modify_user"));
		customToContact.setCreateTime(rs.getTimestamp("create_time"));
		customToContact.setModifyTime(rs.getTimestamp("modify_time"));
		customToContact.setCustomId(rs.getString("custom_id"));
		customToContact.setContactId(rs.getString("contact_id"));
		return customToContact;
	}

	@Override
	public void add(final CustomToContact t) {
		String sql = "insert into t_custom_to_contact(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setString(10, t.getCustomId());
				ps.setString(11, t.getContactId());
			}
			
		});
	}

	@Override
	public void delete(final String id) {
		String sql = "delete from t_custom_to_contact where guid=?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1, id);
			}

		});
	}

	@Override
	public void modify(final CustomToContact t) {
		String sql = "update t_custom_to_contact set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,custom_id=?,contact_id=? where guid=?";
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
				ps.setString(9, t.getCustomId());
				ps.setString(10, t.getContactId());
				ps.setString(11, t.getGuid());
			}
		});
	}

	@Override
	public CustomToContact get(String id) {
		String sql = "select " + COLS + " from t_acc_to_group where guid=?";
		List<CustomToContact> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<CustomToContact>() {

					@Override
					public CustomToContact mapRow(ResultSet rs, int rowNum) throws SQLException {
						return extract(rs);
					}
				});
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Contact> getContacts(String customId) {
		String sql = "select t2.* from t_custom_to_contact t1 left join t_contact t2 on t2.guid=t1.contact_id where t1.custom_id=? and t1.status=? and t2.status=?";
		List<Contact> list = jdbcTemplate.query(sql, new Object[]{customId,DB_STATUS.STATUS_ACTIVE,DB_STATUS.STATUS_ACTIVE},new RowMapper<Contact>(){

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				return contactDao.extract(rs);
			}
			
		});
		return list;
	}

}
