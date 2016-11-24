/**
 * 
 */
package cn.prm.server.dao;

import cn.prm.server.entity.OrderItem;

/**
 * @Title: IOrderItemDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午4:01:04<br>
 * @version v1.0<br>
 */
public interface IOrderItemDao extends IBaseDao<OrderItem> {
    
    /** 
     * @Title: getByOrderId<br>
     * @Description: <br>
     * @param orderId
     * @return
     */
    OrderItem getByOrderId(String orderId);
}
