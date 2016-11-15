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

import cn.prm.server.dao.ISequenceDao;
import cn.prm.server.entity.Sequence;

/**
 * @Title: SequenceDaoImpl.java<br>
 * @Package: cn.prm.server.dao.impl<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午4:48:05<br>
 * @version v1.0<br>
 */
@Repository
public class SequenceDaoImpl implements ISequenceDao {

    private static final String COLS = "guid,std_name,std_code,status,memo,create_user,modify_user,create_time,modify_time,seq_key,seq_value,min_value,max_value,increment";

    @Autowired
    JdbcTemplate                jdbcTemplate;
    
    @Override
    public Sequence extract(ResultSet rs) throws SQLException, DataAccessException {
        Sequence sequence = new Sequence();
        sequence.setGuid(rs.getString("guid"));
        sequence.setStdName(rs.getString("std_name"));
        sequence.setStdCode(rs.getInt("std_code"));
        sequence.setStatus(rs.getInt("status"));
        sequence.setMemo(rs.getString("memo"));
        sequence.setCreateUser(rs.getString("create_user"));
        sequence.setModifyUser(rs.getString("modify_user"));
        sequence.setCreateTime(rs.getTimestamp("create_time"));
        sequence.setModifyTime(rs.getTimestamp("modify_time"));
        sequence.setSeqKey(rs.getString("seq_key"));
        sequence.setSeqValue(rs.getInt("seq_value"));
        sequence.setMinValue(rs.getInt("min_value"));
        sequence.setMaxValue(rs.getInt("max_value"));
        sequence.setIncrement(rs.getInt("increment"));
        return sequence;
    }

    @Override
    public void add(final Sequence t) {
        String sql = "insert into t_sequence(" + COLS + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setString(10, t.getSeqKey());
                ps.setObject(11, t.getSeqValue());
                ps.setObject(12,t.getMinValue());
                ps.setObject(13,t.getMaxValue());
                ps.setObject(14,t.getIncrement());
            }

        });
    }

    @Override
    public void delete(final String id) {
        String sql = "delete from t_sequence where guid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pst) throws SQLException {
                pst.setString(1, id);
            }

        });
    }

    @Override
    public void modify(final Sequence t) {
        String sql = "update t_sequence set std_name=?,std_code=?,status=?,memo=?,create_user=?,modify_user=?,create_time=?,modify_time=?,seq_key=?,seq_value=?,min_value=?,max_value=?,increment=? where guid=?";
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
                ps.setString(9, t.getSeqKey());
                ps.setObject(10, t.getSeqValue());
                ps.setObject(11,t.getMinValue());
                ps.setObject(12,t.getMaxValue());
                ps.setObject(13,t.getIncrement());
                ps.setString(14, t.getGuid());
            }
        });
    }

    @Override
    public Sequence get(String id) {
        String sql = "select " + COLS + " from t_sequence where guid=?";
        List<Sequence> list = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Sequence>() {

            @Override
            public Sequence mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Sequence getByKey(String seqKey) {
        String sql = "select " + COLS + " from t_sequence where seq_key=?";
        List<Sequence> list = jdbcTemplate.query(sql, new Object[] { seqKey }, new RowMapper<Sequence>() {

            @Override
            public Sequence mapRow(ResultSet rs, int rowNum) throws SQLException {
                return extract(rs);
            }
        });
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
