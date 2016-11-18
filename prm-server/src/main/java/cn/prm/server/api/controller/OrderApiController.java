/**
 * 
 */
package cn.prm.server.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;
import cn.prm.server.dto.PageDto;
import cn.prm.server.dto.bean.OrderDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.OrderPageForm;
import cn.prm.server.form.PageBaseForm;
import cn.prm.server.service.OrderService;

/**
 * @Title: OrderApiController.java<br>
 * @Package: cn.prm.server.api.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月17日 下午5:26:32<br>
 * @version v1.0<br>
 */
@RestController
@RequestMapping("/api/order")
public class OrderApiController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    @Autowired
    OrderService                orderService;

    /**
     * @Title: list<br>
     * @Description: <br>
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, OrderPageForm form) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            form.checkFields();
            PageDto<OrderDto> page = orderService.getOrders(currUser, form.getS(), form.getSearch(), form.getOrder(), form.getOffset(), form.getLimit());
            page.setCode(RESPONSE_CODE.CODE_SUCCESS);
            return page;
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }

    }
}
