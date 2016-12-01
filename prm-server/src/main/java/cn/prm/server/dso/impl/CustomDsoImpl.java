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
import cn.prm.server.dto.bean.CustomDto;

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
    public List<CustomDto> getCustoms(String groupId) {
        String sql = "select t2.*,t3.phone as phone,t3.email as email,t3.qq as qq,t3.weixin as weixin from t_group_to_custom t1 join t_custom t2 on t2.guid=t1.custom_id join t_contact as t3 on t2.guid=t3.custom_id where t1.group_id=? and t1.status=? and t2.status=? and t3.status=? order by t2.create_time";

        List<CustomDto> list = jdbcTemplate.query(sql,
                new Object[] { groupId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE },
                new RowMapper<CustomDto>() {

                    @Override
                    public CustomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CustomDto customDto = new CustomDto();
                        customDto.setId(rs.getString("guid"));
                        customDto.setName(rs.getString("std_name"));
                        customDto.setSex(rs.getBoolean("sex"));
                        customDto.setPhone(rs.getString("phone"));
                        customDto.setEmail(rs.getString("email"));
                        customDto.setQq(rs.getString("qq"));
                        customDto.setWeixin(rs.getString("weixin"));
                        return customDto;
                    }

                });
        return list;
    }

    @Override
    public List<CustomDto> getCustoms(String groupId, String search) {
        String sql = "select t2.*,t3.phone as phone,t3.email as email,t3.qq as qq,t3.weixin as weixin from t_group_to_custom t1 join t_custom t2 on t2.guid=t1.custom_id join t_contact as t3 on t2.guid=t3.custom_id where t1.group_id=? and (t2.std_name like ? or t3.phone like ? or t3.email like ? or t3.qq like ? or t3.weixin like ?) and t1.status=? and t2.status=? and t3.status=? order by t2.create_time";

        List<CustomDto> list = jdbcTemplate.query(sql,
                new Object[] { groupId, search, search, search, search, search, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE },
                new RowMapper<CustomDto>() {

                    @Override
                    public CustomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CustomDto customDto = new CustomDto();
                        customDto.setId(rs.getString("guid"));
                        customDto.setName(rs.getString("std_name"));
                        customDto.setSex(rs.getBoolean("sex"));
                        customDto.setPhone(rs.getString("phone"));
                        customDto.setEmail(rs.getString("email"));
                        customDto.setQq(rs.getString("qq"));
                        customDto.setWeixin(rs.getString("weixin"));
                        return customDto;
                    }

                });
        return list;
    }
    
    @Override
    public List<CustomDto> getCustoms(String groupId, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t2.*,t3.phone as phone,t3.email as email,t3.qq as qq,t3.weixin as weixin from t_group_to_custom t1 join t_custom t2 on t2.guid=t1.custom_id join t_contact as t3 on t2.guid=t3.custom_id where t1.group_id=? and t1.status=? and t2.status=? and t3.status=? order by t2.create_time limit ?,?";

        List<CustomDto> list = jdbcTemplate.query(sql,
                new Object[] { groupId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<CustomDto>() {

                    @Override
                    public CustomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CustomDto customDto = new CustomDto();
                        customDto.setId(rs.getString("guid"));
                        customDto.setName(rs.getString("std_name"));
                        customDto.setSex(rs.getBoolean("sex"));
                        customDto.setPhone(rs.getString("phone"));
                        customDto.setEmail(rs.getString("email"));
                        customDto.setQq(rs.getString("qq"));
                        customDto.setWeixin(rs.getString("weixin"));
                        return customDto;
                    }

                });
        return list;
    }
    
    @Override
    public List<CustomDto> getCustoms(String groupId, String search, int offset, int limit) {
        String sql = "select SQL_CALC_FOUND_ROWS t2.*,t3.phone as phone,t3.email as email,t3.qq as qq,t3.weixin as weixin from t_group_to_custom t1 join t_custom t2 on t2.guid=t1.custom_id join t_contact as t3 on t2.guid=t3.custom_id where t1.group_id=? and (t2.std_name like ? or t3.phone like ? or t3.email like ? or t3.qq like ? or t3.weixin like ?) and t1.status=? and t2.status=? and t3.status=? order by t2.create_time limit ?,?";

        List<CustomDto> list = jdbcTemplate.query(sql,
                new Object[] { groupId, search, search, search, search, search, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE, offset, limit },
                new RowMapper<CustomDto>() {

                    @Override
                    public CustomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CustomDto customDto = new CustomDto();
                        customDto.setId(rs.getString("guid"));
                        customDto.setName(rs.getString("std_name"));
                        customDto.setSex(rs.getBoolean("sex"));
                        customDto.setPhone(rs.getString("phone"));
                        customDto.setEmail(rs.getString("email"));
                        customDto.setQq(rs.getString("qq"));
                        customDto.setWeixin(rs.getString("weixin"));
                        return customDto;
                    }

                });
        return list;
    }
    
    @Override
    public int getCustomCount() {
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
