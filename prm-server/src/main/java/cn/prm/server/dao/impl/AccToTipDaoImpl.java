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

import cn.prm.server.dao.IAccToTipDao;
import cn.prm.server.entity.AccToTip;

/**
 * @Title: AccToTipDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午5:23:59<br>
 * @version v1.0<br>
 */
@Repository
public class AccToTipDaoImpl implements IAccToTipDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,acc_id,tip_id";

    @Autowired
    JdbcTemplate                jdbcTemplate;

    @Override
    public AccToTip extract(ResultSet rs) throws SQLException, DataAccessException {
        AccToTip accToTip = new AccToTip();
        accToTip.setGuid(rs.getString("guid"));
        accToTip.setStdName(rs.getString("std_name"));
        accToTip.setStdCode(rs.getInt("std_code"));
        accToTip.setStatus(rs.getInt("status"));
        accToTip.setMemo(rs.getString("memo"));
        accToTip.setCreateUser(rs.getString("create_user"));
        accToTip.setModifyUser(rs.getString("modify_user"));
        accToTip.setCreateTime(rs.getTimestamp("create_time"));
        accToTip.setModifyTime(rs.getTimestamp("modify_time"));
        accToTip.setAccId(rs.getString("acc_id"));
        accToTip.setTipId(rs.getString("tip_id"));
        return accToTip;
    }

    @Override
    public void add(final AccToTip t) {
        String sql = "insert into t_acc_to_tip(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getAccId());
                ps.setString(11, t.getTipId());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_acc_to_tip where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final AccToTip t) {
        String sql = "update t_acc_to_tip set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,acc_id=?,tip_id=? where guid=?";
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
                ps.setString(9, t.getAccId());
                ps.setString(10, t.getTipId());
                ps.setString(11, t.getGuid());
            }
        });
    }

    @Override
    public AccToTip get(String id) {
        String sql = "select " + COLS + " from t_acc_to_tip where guid=?";
        List<AccToTip> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<AccToTip>() {

            @Override
            public AccToTip mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
