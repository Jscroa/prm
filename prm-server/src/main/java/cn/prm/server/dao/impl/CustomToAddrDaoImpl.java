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
import cn.prm.server.dao.IAddressDao;
import cn.prm.server.dao.ICustomToAddrDao;
import cn.prm.server.entity.Address;
import cn.prm.server.entity.CustomToAddr;

/**
 * @Title: CustomToAddrDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:30:00<br>
 * @version v1.0<br>
 */
@Repository
public class CustomToAddrDaoImpl implements ICustomToAddrDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,custom_id,addr_id";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Autowired
    IAddressDao                 addressDao;

    @Override
    public CustomToAddr extract(ResultSet rs) throws SQLException, DataAccessException {
        CustomToAddr customToAddr = new CustomToAddr();
        customToAddr.setGuid(rs.getString("guid"));
        customToAddr.setStdName(rs.getString("std_name"));
        customToAddr.setStdCode(rs.getInt("std_code"));
        customToAddr.setStatus(rs.getInt("status"));
        customToAddr.setMemo(rs.getString("memo"));
        customToAddr.setCreateUser(rs.getString("create_user"));
        customToAddr.setModifyUser(rs.getString("modify_user"));
        customToAddr.setCreateTime(rs.getTimestamp("create_time"));
        customToAddr.setModifyTime(rs.getTimestamp("modify_time"));
        customToAddr.setCustomId(rs.getString("custom_id"));
        customToAddr.setAddrId(rs.getString("addr_id"));
        return customToAddr;
    }

    @Override
    public void add(final CustomToAddr t) {
        String sql = "insert into t_custom_to_addr(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(11, t.getAddrId());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_custom_to_addr where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final CustomToAddr t) {
        String sql = "update t_custom_to_addr set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,custom_id=?,addr_id=? where guid=?";
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
                ps.setString(10, t.getAddrId());
                ps.setString(11, t.getGuid());
            }
        });
    }

    @Override
    public CustomToAddr get(String id) {
        String sql = "select " + COLS + " from t_custom_to_addr where guid=?";
        List<CustomToAddr> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<CustomToAddr>() {

            @Override
            public CustomToAddr mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Address> getAddresses(String customId) {
        String sql = "select t2.* from t_custom_to_addr t1 left join t_address t2 on t2.guid=t1.addr_id where t1.custom_id=? and t1.status=? and t2.status=?";
        List<Address> list = jdbcTemplate.query(sql,
                new Object[] { customId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE }, new RowMapper<Address>() {

                    @Override
                    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return addressDao.extract(rs);
                    }

                });
        return list;
    }

    @Override
    public List<CustomToAddr> getbyAddrId(String addrId) {
        String sql = "select " + COLS + " from t_custom_to_addr where addr_id=?";
        List<CustomToAddr> list = jdbcTemplate.query(sql, new Object[] { addrId }, new RowMapper<CustomToAddr>() {

            @Override
            public CustomToAddr mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        return list;
    }

}
