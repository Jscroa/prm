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

import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.dao.IOrderDao;
import cn.prm.server.entity.Order;
import cn.prm.server.entity.Order.OrderWithPay;

/**
 * @Title: OrderDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午5:39:48<br>
 * @version v1.0<br>
 */
@Repository
public class OrderDaoImpl implements IOrderDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,custom_id,order_type,price";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public Order extract(ResultSet rs) throws SQLException, DataAccessException {
        Order order = new Order();
        order.setGuid(rs.getString("guid"));
        order.setStdName(rs.getString("std_name"));
        order.setStdCode(rs.getInt("std_code"));
        order.setStatus(rs.getInt("status"));
        order.setMemo(rs.getString("memo"));
        order.setCreateUser(rs.getString("create_user"));
        order.setModifyUser(rs.getString("modify_user"));
        order.setCreateTime(rs.getTimestamp("create_time"));
        order.setModifyTime(rs.getTimestamp("modify_time"));
        order.setCustomId(rs.getString("custom_id"));
        order.setOrderType(rs.getString("order_type"));
        order.setPrice(rs.getDouble("price"));
        return order;
    }

    @Override
    public void add(final Order t) {
        String sql = "insert into t_order(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getCustomId());
                ps.setString(11, t.getOrderType());
                ps.setDouble(12, t.getPrice());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_order where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Order t) {
        String sql = "update t_order set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,custom_id=?,order_type=?,price=? where guid=?";
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
                ps.setString(10, t.getOrderType());
                ps.setDouble(11, t.getPrice());
                ps.setString(12, t.getGuid());

            }
        });
    }

    @Override
    public Order get(String id) {
        String sql = "select " + COLS + " from t_order where guid=?";
        List<Order> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Order>() {

            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<OrderWithPay> getOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t6.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_payment as t6 on t5.guid=t6.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? order by t5.create_time desc limit ?,?";
        List<OrderWithPay> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderWithPay>() {

                    @Override
                    public OrderWithPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderWithPay orderWithPay = new OrderWithPay();
                        orderWithPay.setGuid(rs.getString("guid"));
                        orderWithPay.setStdName(rs.getString("std_name"));
                        orderWithPay.setStdCode(rs.getInt("std_code"));
                        orderWithPay.setStatus(rs.getInt("status"));
                        orderWithPay.setMemo(rs.getString("memo"));
                        orderWithPay.setCreateUser(rs.getString("create_user"));
                        orderWithPay.setModifyUser(rs.getString("modify_user"));
                        orderWithPay.setCreateTime(rs.getTimestamp("create_time"));
                        orderWithPay.setModifyTime(rs.getTimestamp("modify_time"));
                        orderWithPay.setCustomId(rs.getString("custom_id"));
                        orderWithPay.setOrderType(rs.getString("order_type"));
                        orderWithPay.setPrice(rs.getDouble("price"));
                        orderWithPay.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return orderWithPay;
                    }
                });
        return list;
    }

    @Override
    public List<OrderWithPay> getPayedOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t6.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_payment as t6 on t5.guid=t6.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status=? order by t5.create_time desc limit ?,?";
        List<OrderWithPay> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderWithPay>() {

                    @Override
                    public OrderWithPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderWithPay orderWithPay = new OrderWithPay();
                        orderWithPay.setGuid(rs.getString("guid"));
                        orderWithPay.setStdName(rs.getString("std_name"));
                        orderWithPay.setStdCode(rs.getInt("std_code"));
                        orderWithPay.setStatus(rs.getInt("status"));
                        orderWithPay.setMemo(rs.getString("memo"));
                        orderWithPay.setCreateUser(rs.getString("create_user"));
                        orderWithPay.setModifyUser(rs.getString("modify_user"));
                        orderWithPay.setCreateTime(rs.getTimestamp("create_time"));
                        orderWithPay.setModifyTime(rs.getTimestamp("modify_time"));
                        orderWithPay.setCustomId(rs.getString("custom_id"));
                        orderWithPay.setOrderType(rs.getString("order_type"));
                        orderWithPay.setPrice(rs.getDouble("price"));
                        orderWithPay.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return orderWithPay;
                    }
                });
        return list;
    }

    @Override
    public List<OrderWithPay> getUnPayedOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t6.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_payment as t6 on t5.guid=t6.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status!=? order by t5.create_time desc limit ?,?";
        List<OrderWithPay> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderWithPay>() {

                    @Override
                    public OrderWithPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderWithPay orderWithPay = new OrderWithPay();
                        orderWithPay.setGuid(rs.getString("guid"));
                        orderWithPay.setStdName(rs.getString("std_name"));
                        orderWithPay.setStdCode(rs.getInt("std_code"));
                        orderWithPay.setStatus(rs.getInt("status"));
                        orderWithPay.setMemo(rs.getString("memo"));
                        orderWithPay.setCreateUser(rs.getString("create_user"));
                        orderWithPay.setModifyUser(rs.getString("modify_user"));
                        orderWithPay.setCreateTime(rs.getTimestamp("create_time"));
                        orderWithPay.setModifyTime(rs.getTimestamp("modify_time"));
                        orderWithPay.setCustomId(rs.getString("custom_id"));
                        orderWithPay.setOrderType(rs.getString("order_type"));
                        orderWithPay.setPrice(rs.getDouble("price"));
                        orderWithPay.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return orderWithPay;
                    }
                });
        return list;
    }

    @Override
    public int getOrderCount() {
        String sql = "select FOUND_ROWS()";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return 0;
    }
}
