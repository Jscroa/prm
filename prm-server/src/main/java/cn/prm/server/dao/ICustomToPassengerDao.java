/**
 * 
 */
package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.CustomToPassenger;
import cn.prm.server.entity.Passenger;

/**
 * @Title: ICustomToPassengerDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午3:59:24<br>
 * @version v1.0<br>
 */
public interface ICustomToPassengerDao extends IBaseDao<CustomToPassenger> {

    /**
     * @Title: getPassengers<br>
     * @Description: <br>
     * @param customId
     * @return
     */
    List<Passenger> getPassengers(String customId);

}
