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

import cn.prm.server.dao.IOrderItemDao;
import cn.prm.server.entity.OrderItem;

/**
 * @Title: OrderItemDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午5:59:03<br>
 * @version v1.0<br>
 */
@Repository
public class OrderItemDaoImpl implements IOrderItemDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,order_id,passenger_id,addr_from,addr_to,trip_type,go_time,return_time,price";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public OrderItem extract(ResultSet rs) throws SQLException, DataAccessException {
        OrderItem orderItem = new OrderItem();
        orderItem.setGuid(rs.getString("guid"));
        orderItem.setStdName(rs.getString("std_name"));
        orderItem.setStdCode(rs.getInt("std_code"));
        orderItem.setStatus(rs.getInt("status"));
        orderItem.setMemo(rs.getString("memo"));
        orderItem.setCreateUser(rs.getString("create_user"));
        orderItem.setModifyUser(rs.getString("modify_user"));
        orderItem.setCreateTime(rs.getTimestamp("create_time"));
        orderItem.setModifyTime(rs.getTimestamp("modify_time"));
        orderItem.setOrderId(rs.getString("order_id"));
        orderItem.setPassengerId(rs.getString("passenger_id"));
        orderItem.setAddrFrom(rs.getString("addr_from"));
        orderItem.setAddrTo(rs.getString("addr_to"));
        orderItem.setTripType(rs.getInt("trip_type"));
        orderItem.setGoTime(rs.getTimestamp("go_time"));
        orderItem.setReturnTime(rs.getTimestamp("return_time"));
        orderItem.setPrice(rs.getDouble("price"));
        return orderItem;
    }

    @Override
    public void add(final OrderItem t) {
        String sql = "insert into t_order_item(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(11, t.getPassengerId());
                ps.setString(12, t.getAddrFrom());
                ps.setString(13, t.getAddrTo());
                ps.setInt(14, t.getTripType());
                ps.setTimestamp(15, t.getGoTime());
                ps.setTimestamp(16, t.getReturnTime());
                ps.setDouble(17, t.getPrice());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_order_item where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final OrderItem t) {

        String sql = "update t_order_item set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,order_id=?,passenger_id=?,addr_from=?,addr_to=?,trip_type=?,go_time=?,return_time=?,price=? where guid=?";
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
                ps.setString(10, t.getPassengerId());
                ps.setString(11, t.getAddrFrom());
                ps.setString(12, t.getAddrTo());
                ps.setInt(13, t.getTripType());
                ps.setTimestamp(14, t.getGoTime());
                ps.setTimestamp(15, t.getReturnTime());
                ps.setDouble(16, t.getPrice());
                ps.setString(17, t.getGuid());
            }
        });
    }

    @Override
    public OrderItem get(String id) {
        String sql = "select " + COLS + " from t_order_item where guid=?";
        List<OrderItem> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<OrderItem>() {

            @Override
            public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
