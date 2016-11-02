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
 * @Title: CenterController.java<br>
 * @Package: cn.prm.server.page.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:41:07<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping("/center")
public class CenterController extends BaseController {
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
        mav.setViewName("center/center");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }
    
    /** 
     * @Title: centerIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("centerIndex")
    public ModelAndView centerIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("center/center_index");
        return mav;
    }
}
