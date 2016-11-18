/**
 * 
 */
package cn.prm.server.dso.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.dso.IOrderDso;
import cn.prm.server.dto.bean.OrderDto;

/**
 * @Title: OrderDsoImpl.java<br>
 * @Package: cn.prm.server.dso.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月18日 上午10:30:03<br>
 * @version v1.0<br>
 */
@Repository
public class OrderDsoImpl implements IOrderDso {
    
    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public List<OrderDto> getOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t4.std_name as custom_name,t6.std_name as address_str,t7.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_address as t6 on t5.address=t6.guid join t_payment as t7 on t5.guid=t7.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status=? order by t5.create_time desc limit ?,?";
        List<OrderDto> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderDto>() {

                    @Override
                    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderDto dto = new OrderDto();
                        dto.setId(rs.getString("guid"));
                        dto.setOrderNum(rs.getString("std_name"));
                        dto.setCustomId(rs.getString("custom_id"));
                        dto.setCustomName(rs.getString("custom_name"));
                        dto.setAddress(rs.getString("address"));
                        dto.setAddressStr(rs.getString("address_str"));
                        dto.setOrderTime(rs.getString("create_time"));
                        dto.setPrice(rs.getDouble("price"));
                        dto.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return dto;
                    }
                });
        return list;
    }

    @Override
    public List<OrderDto> getPayedOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t4.guid as custom_id,t4.std_name as custom_name,t6.std_name as address_str,t7.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_address as t6 on t5.address=t6.guid join t_payment as t7 on t5.guid=t7.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status=? and t7.status=? order by t5.create_time desc limit ?,?";
        List<OrderDto> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderDto>() {

                    @Override
                    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderDto dto = new OrderDto();
                        dto.setId(rs.getString("guid"));
                        dto.setOrderNum(rs.getString("std_name"));
                        dto.setCustomId(rs.getString("custom_id"));
                        dto.setCustomName(rs.getString("custom_name"));
                        dto.setAddress(rs.getString("address"));
                        dto.setAddressStr(rs.getString("address_str"));
                        dto.setOrderTime(rs.getString("create_time"));
                        dto.setPrice(rs.getDouble("price"));
                        dto.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return dto;
                    }
                });
        return list;
    }

    @Override
    public List<OrderDto> getUnPayedOrders(String accountId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t5.*,t4.guid as custom_id,t4.std_name as custom_name,t6.std_name as address_str,t7.status as pay_status from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_order as t5 on t4.guid=t5.custom_id join t_address as t6 on t5.address=t6.guid join t_payment as t7 on t5.guid=t7.order_id where t1.acc_id=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status=? and t7.status!=? order by t5.create_time desc limit ?,?";
        List<OrderDto> list = jdbcTemplate.query(sql,
                new Object[] { accountId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE,
                        DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<OrderDto>() {

                    @Override
                    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderDto dto = new OrderDto();
                        dto.setId(rs.getString("guid"));
                        dto.setOrderNum(rs.getString("std_name"));
                        dto.setCustomId(rs.getString("custom_id"));
                        dto.setCustomName(rs.getString("custom_name"));
                        dto.setAddress(rs.getString("address"));
                        dto.setAddressStr(rs.getString("address_str"));
                        dto.setOrderTime(rs.getString("create_time"));
                        dto.setPrice(rs.getDouble("price"));
                        dto.setIsPay(rs.getInt("pay_status") == DB_STATUS.STATUS_ACTIVE);
                        return dto;
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
