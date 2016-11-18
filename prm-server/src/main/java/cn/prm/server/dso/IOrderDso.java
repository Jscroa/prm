/**
 * 
 */
package cn.prm.server.dso;

import java.util.List;

import cn.prm.server.dto.bean.OrderDto;

/**
 * @Title: IOrderDso.java<br>
 * @Package: cn.prm.server.dso<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月18日 上午10:29:54<br>
 * @version v1.0<br>
 */
public interface IOrderDso {
    /** 
     * @Title: getOrders<br>
     * @Description: 所有订单<br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderDto> getOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getOrders<br>
     * @Description: 已支付订单<br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderDto> getPayedOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getUnPayedOrders<br>
     * @Description: 未支付订单<br>
     * @param accountId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderDto> getUnPayedOrders(String accountId,int offset, int limit);
    
    /** 
     * @Title: getOrderCount<br>
     * @Description: 总记录数<br>
     * @return
     */
    int getOrderCount();
}
