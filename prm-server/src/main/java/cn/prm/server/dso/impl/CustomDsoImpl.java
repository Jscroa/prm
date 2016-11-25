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
import cn.prm.server.dso.ICustomDso;

/**
 * @Title: CustomDsoImpl.java<br>
 * @Package: cn.prm.server.dso.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月18日 上午10:15:44<br>
 * @version v1.0<br>
 */
@Repository
public class CustomDsoImpl implements ICustomDso {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> checkCustomOwn(String accId, String customId) {
        String sql = "select t4.guid as custom_id from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid where t1.acc_id=? and t4.guid=? and t1.status=? and t2.status=? and t3.status=? and t4.status=?";
        List<String> list = jdbcTemplate.query(sql, new Object[] { accId, customId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE }, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("custom_id");
            }

        });
        return list;
    }

    @Override
    public List<String> checkAddressOwn(String accId, String addressId) {
        String sql = "select t6.guid as addr_id from t_acc_to_group as t1 join t_acc_group as t2 on t1.group_id=t2.guid join t_group_to_custom as t3 on t2.guid=t3.group_id join t_custom as t4 on t3.custom_id=t4.guid join t_custom_to_addr as t5 on t4.guid=t5.custom_id join t_address as t6 on t5.addr_id=t6.guid where t1.acc_id=? and t6.guid=? and t1.status=? and t2.status=? and t3.status=? and t4.status=? and t5.status=? and t6.status=?";
        List<String> list = jdbcTemplate.query(sql, new Object[] { accId, addressId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE }, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("addr_id");
            }

        });
        return list;
    }
}
