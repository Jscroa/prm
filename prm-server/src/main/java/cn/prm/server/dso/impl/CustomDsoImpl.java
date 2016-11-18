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
        String sql = "select gc.custom_id as custom_id from t_acc_to_group as ag join t_group_to_custom as gc on ag.group_id=gc.group_id where ag.acc_id=? and gc.custom_id=?;";
        List<String> list = jdbcTemplate.query(sql, new Object[] { accId, customId }, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("custom_id");
            }

        });
        return list;
    }
}
