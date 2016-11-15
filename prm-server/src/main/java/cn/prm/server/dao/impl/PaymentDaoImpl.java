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

import cn.prm.server.dao.IPaymentDao;
import cn.prm.server.entity.Payment;

/**
 * @Title: PaymentDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午6:18:25<br>
 * @version v1.0<br>
 */
@Repository
public class PaymentDaoImpl implements IPaymentDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,order_id,drawee,payee,price";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public Payment extract(ResultSet rs) throws SQLException, DataAccessException {
        Payment payment = new Payment();
        payment.setGuid(rs.getString("guid"));
        payment.setStdName(rs.getString("std_name"));
        payment.setStdCode(rs.getInt("std_code"));
        payment.setStatus(rs.getInt("status"));
        payment.setMemo(rs.getString("memo"));
        payment.setCreateUser(rs.getString("create_user"));
        payment.setModifyUser(rs.getString("modify_user"));
        payment.setCreateTime(rs.getTimestamp("create_time"));
        payment.setModifyTime(rs.getTimestamp("modify_time"));
        payment.setOrderId(rs.getString("order_id"));
        payment.setDrawee(rs.getString("drawee"));
        payment.setPayee(rs.getString("payee"));
        payment.setPrice(rs.getDouble("price"));
        return payment;
    }

    @Override
    public void add(final Payment t) {
        String sql = "insert into t_payment(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getOrderId());
                ps.setString(11, t.getDrawee());
                ps.setString(12, t.getPayee());
                ps.setDouble(13, t.getPrice());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_payment where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Payment t) {
        String sql = "update t_payment set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,order_id=?,drawee=?,payee=?,price=? where guid=?";
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
                ps.setString(9, t.getOrderId());
                ps.setString(10, t.getDrawee());
                ps.setString(11, t.getPayee());
                ps.setDouble(12, t.getPrice());
                ps.setString(13, t.getGuid());
            }
        });
    }

    @Override
    public Payment get(String id) {
        String sql = "select " + COLS + " from t_payment where guid=?";
        List<Payment> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Payment>() {

            @Override
            public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
