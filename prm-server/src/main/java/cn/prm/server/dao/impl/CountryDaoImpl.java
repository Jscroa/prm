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

import cn.prm.server.dao.ICountryDao;
import cn.prm.server.entity.Country;

/**
 * @Title: CountryDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:29:41<br>
 * @version v1.0<br>
 */
@Repository
public class CountryDaoImpl implements ICountryDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,en_name,cn_name";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public Country extract(ResultSet rs) throws SQLException, DataAccessException {
        Country country = new Country();
        country.setGuid(rs.getString("guid"));
        country.setStdName(rs.getString("std_name"));
        country.setStdCode(rs.getInt("std_code"));
        country.setStatus(rs.getInt("status"));
        country.setMemo(rs.getString("memo"));
        country.setCreateUser(rs.getString("create_user"));
        country.setModifyUser(rs.getString("modify_user"));
        country.setCreateTime(rs.getTimestamp("create_time"));
        country.setModifyTime(rs.getTimestamp("modify_time"));
        country.setEnName(rs.getString("en_name"));
        country.setCnName(rs.getString("cn_name"));
        return country;
    }

    @Override
    public void add(final Country t) {
        String sql = "insert into t_country(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getEnName());
                ps.setString(11, t.getCnName());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_country where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Country t) {
        String sql = "update t_country set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,en_name=?,cn_name=? where guid=?";
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
                ps.setString(9, t.getEnName());
                ps.setString(10, t.getCnName());
                ps.setString(11, t.getGuid());
            }
        });
    }

    @Override
    public Country get(String id) {
        String sql = "select " + COLS + " from t_country where guid=?";
        List<Country> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Country> getByStatus(int status, String order) {
        String sql = "select " + COLS + " from t_country where status=? order by " + order;
        List<Country> list = jdbcTemplate.query(sql, new Object[] { status }, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        return list;
    }

}
