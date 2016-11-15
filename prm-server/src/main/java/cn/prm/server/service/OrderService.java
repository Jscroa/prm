/**
 * 
 */
package cn.prm.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.dao.IOrderDao;

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
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    IOrderDao orderDao;
}
