package cn.prm.server.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.MAVHelper;

/**
 * @Title: OrderController.java<br>
 * @Package: cn.prm.server.page.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:42:33<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private MAVHelper mavHelper;

    /**
     * @Title: index<br>
     * @Description: <br>
     * @param request
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("order/order");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }

    /** 
     * @Title: orderIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("orderIndex")
    public ModelAndView orderIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order/order_index");
        return mav;
    }
    
}
