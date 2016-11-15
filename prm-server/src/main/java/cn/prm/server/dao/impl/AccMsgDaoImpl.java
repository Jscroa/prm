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

import cn.prm.server.dao.IAccMsgDao;
import cn.prm.server.entity.AccMsg;

/**
 * @Title: AccountToMessageDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午4:46:46<br>
 * @version v1.0<br>
 */
@Repository
public class AccMsgDaoImpl implements IAccMsgDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,acc_id,msg_type,content,is_read";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public AccMsg extract(ResultSet rs) throws SQLException, DataAccessException {
        AccMsg accMsg = new AccMsg();
        accMsg.setGuid(rs.getString("guid"));
        accMsg.setStdName(rs.getString("std_name"));
        accMsg.setStdCode(rs.getInt("std_code"));
        accMsg.setStatus(rs.getInt("status"));
        accMsg.setMemo(rs.getString("memo"));
        accMsg.setCreateUser(rs.getString("create_user"));
        accMsg.setModifyUser(rs.getString("modify_user"));
        accMsg.setCreateTime(rs.getTimestamp("create_time"));
        accMsg.setModifyTime(rs.getTimestamp("modify_time"));
        accMsg.setAccId(rs.getString("acc_id"));
        accMsg.setMsgType(rs.getString("msg_type"));
        accMsg.setContent(rs.getString("content"));
        accMsg.setIsRead(rs.getBoolean("is_read"));
        return accMsg;
    }

    @Override
    public void add(final AccMsg t) {
        String sql = "insert into t_acc_msg(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getAccId());
                ps.setString(11, t.getMsgType());
                ps.setString(12, t.getContent());
                ps.setObject(13, t.getIsRead());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_acc_msg where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }
        });
    }

    @Override
    public void modify(final AccMsg t) {
        String sql = "update t_acc_msg set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,acc_id=?,msg_type=?,content=?,is_read=? where guid=?";
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
                ps.setString(10, t.getMsgType());
                ps.setString(11, t.getContent());
                ps.setObject(12, t.getIsRead());
                ps.setString(13, t.getGuid());
            }

        });
    }

    @Override
    public AccMsg get(String id) {
        String sql = "select " + COLS + " from t_acc_msg where guid=?";
        List<AccMsg> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<AccMsg>() {

            @Override
            public AccMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
