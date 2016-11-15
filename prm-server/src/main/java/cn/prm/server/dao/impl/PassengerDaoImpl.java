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

import cn.prm.server.dao.IPassengerDao;
import cn.prm.server.entity.Passenger;

/**
 * @Title: PassengerDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午6:04:22<br>
 * @version v1.0<br>
 */
@Repository
public class PassengerDaoImpl implements IPassengerDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,custom_id,country_id,id_card";

    @Autowired
    JdbcTemplate                jdbcTemplate;
    
    @Override
    public Passenger extract(ResultSet rs) throws SQLException, DataAccessException {
        Passenger passenger = new Passenger();
        passenger.setGuid(rs.getString("guid"));
        passenger.setStdName(rs.getString("std_name"));
        passenger.setStdCode(rs.getInt("std_code"));
        passenger.setStatus(rs.getInt("status"));
        passenger.setMemo(rs.getString("memo"));
        passenger.setCreateUser(rs.getString("create_user"));
        passenger.setModifyUser(rs.getString("modify_user"));
        passenger.setCreateTime(rs.getTimestamp("create_time"));
        passenger.setModifyTime(rs.getTimestamp("modify_time"));
        passenger.setCustomId(rs.getString("custom_id"));
        passenger.setCountryId(rs.getString("country_id"));
        passenger.setIdCard(rs.getString("id_card"));
        return passenger;
    }

    @Override
    public void add(final Passenger t) {
        String sql = "insert into t_passenger(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(11, t.getCountryId());
                ps.setString(12, t.getIdCard());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_passenger where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Passenger t) {
        String sql = "update t_passenger set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,custom_id=?,country_id=?,id_card=? where guid=?";
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
                ps.setString(10, t.getCountryId());
                ps.setString(11, t.getIdCard());
                ps.setString(12, t.getGuid());
            }
        });
    }

    @Override
    public Passenger get(String id) {
        String sql = "select " + COLS + " from t_passenger where guid=?";
        List<Passenger> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Passenger>() {

            @Override
            public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
