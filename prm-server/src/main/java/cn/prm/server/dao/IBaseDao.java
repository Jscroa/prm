package cn.prm.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

/**
 * @Title: IBaseDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:08<br>
 * @version v1.0<br>
 * @param <T>
 */
public interface IBaseDao<T> {

    /**
     * @Title: extract<br>
     * @Description: <br>
     * @param rs
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    T extract(ResultSet rs) throws SQLException, DataAccessException;

    /**
     * @Title: add<br>
     * @Description: 增<br>
     * @param t
     */
    void add(T t);

    /**
     * @Title: delete<br>
     * @Description: 删<br>
     * @param id
     */
    void delete(String id);

    /**
     * @Title: modify<br>
     * @Description: 改<br>
     * @param t
     */
    void modify(T t);

    /**
     * @Title: get<br>
     * @Description: 查<br>
     * @param id
     * @return
     */
    T get(String id);

}
