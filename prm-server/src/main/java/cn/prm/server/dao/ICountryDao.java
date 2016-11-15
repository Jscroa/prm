package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Country;

/**
 * @Title: ICountryDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:54<br>
 * @version v1.0<br>
 */
public interface ICountryDao extends IBaseDao<Country> {

    /**
     * @Title: getAll<br>
     * @Description: <br>
     * @return
     */
    List<Country> getAll();
}
