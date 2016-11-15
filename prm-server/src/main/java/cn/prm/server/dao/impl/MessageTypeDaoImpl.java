/**
 * 
 */
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

import cn.prm.server.dao.IMessageTypeDao;
import cn.prm.server.entity.MessageType;

/**
 * @Title: MessageTypeDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午5:35:12<br>
 * @version v1.0<br>
 */
@Repository
public class MessageTypeDaoImpl implements IMessageTypeDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public MessageType extract(ResultSet rs) throws SQLException, DataAccessException {
        MessageType messageType = new MessageType();
        messageType.setGuid(rs.getString("guid"));
        messageType.setStdName(rs.getString("std_name"));
        messageType.setStdCode(rs.getInt("std_code"));
        messageType.setStatus(rs.getInt("status"));
        messageType.setMemo(rs.getString("memo"));
        messageType.setCreateUser(rs.getString("create_user"));
        messageType.setModifyUser(rs.getString("modify_user"));
        messageType.setCreateTime(rs.getTimestamp("create_time"));
        messageType.setModifyTime(rs.getTimestamp("modify_time"));
        return messageType;
    }

    @Override
    public void add(final MessageType t) {
        String sql = "insert into t_msg_type(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
        String sql = "delete from t_msg_type where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final MessageType t) {
        String sql = "update t_msg_type set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=? where guid=?";
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
    public MessageType get(String id) {
        String sql = "select " + COLS + " from t_msg_type where guid=?";
        List<MessageType> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<MessageType>() {

            @Override
            public MessageType mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
