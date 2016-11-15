/**
 * 
 */
package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Order;
import cn.prm.server.entity.Order.OrderWithPay;

/**
 * @Title: IOrderDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午4:00:28<br>
 * @version v1.0<br>
 */
public interface IOrderDao extends IBaseDao<Order> {
    
    /** 
     * @Title: getOrders<br>
     * @Description: <br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderWithPay> getOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getOrders<br>
     * @Description: <br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderWithPay> getPayedOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getUnPayedOrders<br>
     * @Description: <br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderWithPay> getUnPayedOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getOrderCount<br>
     * @Description: <br>
     * @return
     */
    int getOrderCount();
}
