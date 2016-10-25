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

import cn.prm.server.dao.IContactDao;
import cn.prm.server.entity.Contact;

/**
 * @Title: ContactDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:29:38<br>
 * @version v1.0<br>
 */
@Repository
public class ContactDaoImpl implements IContactDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public Contact extract(ResultSet rs) throws SQLException, DataAccessException {
        Contact contact = new Contact();
        contact.setGuid(rs.getString("guid"));
        contact.setStdName(rs.getString("std_name"));
        contact.setStdCode(rs.getInt("std_code"));
        contact.setStatus(rs.getInt("status"));
        contact.setMemo(rs.getString("memo"));
        contact.setCreateUser(rs.getString("create_user"));
        contact.setModifyUser(rs.getString("modify_user"));
        contact.setCreateTime(rs.getTimestamp("create_time"));
        contact.setModifyTime(rs.getTimestamp("modify_time"));
        return contact;
    }

    @Override
    public void add(final Contact t) {
        String sql = "insert into t_contact(" + COLS + ") values(?,?,?,?,?,?,?,?,?)";
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
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_contact where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Contact t) {
        String sql = "update t_contact set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=? where guid=?";
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
    public Contact get(String id) {
        String sql = "select " + COLS + " from t_contact where guid=?";
        List<Contact> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Contact>() {

            @Override
            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
