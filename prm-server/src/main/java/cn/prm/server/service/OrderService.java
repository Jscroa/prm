/**
 * 
 */
package cn.prm.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.dao.IOrderDao;
import cn.prm.server.dso.IOrderDso;
import cn.prm.server.dto.PageDto;
import cn.prm.server.dto.bean.OrderDto;
import cn.prm.server.exception.BusinessException;

/**
 * @Title: OrderService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午4:25:43<br>
 * @version v1.0<br>
 */
@Service(value = "orderService")
public class OrderService {
    
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    IOrderDao orderDao;
    @Autowired
    IOrderDso orderDso;
    
    /** 
     * @Title: getOrders<br>
     * @Description: <br>
     * @param currUser
     * @param s
     * @param search
     * @param order
     * @param offset
     * @param limit
     * @return
     * @throws BusinessException 
     */
    public PageDto<OrderDto> getOrders(CurrUser currUser, String s,String search, String order, int offset, int limit) throws BusinessException{
        List<OrderDto> dtos;
        if (s==null || "".equals(s)|| "all".equals(s)) {
            dtos = orderDso.getOrders(currUser.getGuid(), offset, limit);
        } else if("payed".equals(s)) {
            dtos = orderDso.getPayedOrders(currUser.getGuid(), offset, limit);
        } else if("unpayed".equals(s)) {
            dtos = orderDso.getUnPayedOrders(currUser.getGuid(), offset, limit);
        } else {
            throw new BusinessException("请求参数不正确");
        }
        int total = orderDso.getOrderCount();
        PageDto<OrderDto> page = new PageDto<>();
        page.setRows(dtos);
        page.setTotal(total);
        page.setPage(offset / limit + 1);
        return page;
    }
}
