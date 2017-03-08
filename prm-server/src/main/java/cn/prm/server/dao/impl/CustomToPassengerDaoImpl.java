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
import cn.prm.server.dao.ICustomToPassengerDao;
import cn.prm.server.dao.IPassengerDao;
import cn.prm.server.entity.CustomToAddr;
import cn.prm.server.entity.CustomToPassenger;
import cn.prm.server.entity.Passenger;

/**
 * @Title: CustomToPassengerDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午5:29:34<br>
 * @version v1.0<br>
 */
@Repository
public class CustomToPassengerDaoImpl implements ICustomToPassengerDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,custom_id,passenger_id";

    @Autowired
    JdbcTemplate                jdbcTemplate;
    @Autowired
    IPassengerDao               passengerDao;

    @Override
    public CustomToPassenger extract(ResultSet rs) throws SQLException, DataAccessException {
        CustomToPassenger customToPassenger = new CustomToPassenger();
        customToPassenger.setGuid(rs.getString("guid"));
        customToPassenger.setStdName(rs.getString("std_name"));
        customToPassenger.setStdCode(rs.getInt("std_code"));
        customToPassenger.setStatus(rs.getInt("status"));
        customToPassenger.setMemo(rs.getString("memo"));
        customToPassenger.setCreateUser(rs.getString("create_user"));
        customToPassenger.setModifyUser(rs.getString("modify_user"));
        customToPassenger.setCreateTime(rs.getTimestamp("create_time"));
        customToPassenger.setModifyTime(rs.getTimestamp("modify_time"));
        customToPassenger.setCustomId(rs.getString("custom_id"));
        customToPassenger.setPassengerId(rs.getString("passenger_id"));
        return customToPassenger;
    }

    @Override
    public void add(final CustomToPassenger t) {
        String sql = "insert into t_custom_to_psg(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(11, t.getPassengerId());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_custom_to_psg where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final CustomToPassenger t) {
        String sql = "update t_custom_to_psg set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,custom_id=?,passenger_id=? where guid=?";
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
                ps.setString(10, t.getPassengerId());
                ps.setString(11, t.getGuid());
            }
        });
    }

    @Override
    public CustomToPassenger get(String id) {
        String sql = "select " + COLS + " from t_custom_to_psg where guid=?";
        List<CustomToPassenger> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<CustomToPassenger>() {

            @Override
            public CustomToPassenger mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Passenger> getPassengers(String customId) {
        String sql = "select t2.* from t_custom_to_psg t1 left join t_passenger t2 on t2.guid=t1.passenger_id where t1.custom_id=? and t1.status=? and t2.status=?";
        List<Passenger> list = jdbcTemplate.query(sql,
                new Object[] { customId, DB_STATUS.STATUS_ACTIVE, DB_STATUS.STATUS_ACTIVE },
                new RowMapper<Passenger>() {

                    @Override
                    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return passengerDao.extract(rs);
                    }
                });
        return list;
    }

    @Override
    public List<CustomToPassenger> getByPsgId(String psgId) {
        String sql = "select " + COLS + " from t_custom_to_psg where passenger_id=?";
        List<CustomToPassenger> list = jdbcTemplate.query(sql, new Object[] { psgId }, new RowMapper<CustomToPassenger>() {

            @Override
            public CustomToPassenger mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        return list;
    }
    
}
