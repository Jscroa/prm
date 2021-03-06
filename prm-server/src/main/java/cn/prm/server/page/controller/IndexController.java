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
 * @Title: IndexController.java<br>
 * @Package: cn.prm.server.page.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:41:38<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping()
public class IndexController extends BaseController {

    @Autowired
    private MAVHelper mavHelper;

    /**
     * @Title: index<br>
     * @Description: 主页<br>
     * @param request
     * @return
     */
    @RequestMapping({ "", "/index" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;

    }

    /**
     * @Title: login<br>
     * @Description: 登陆<br>
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @Title: logout<br>
     * @Description: 注销<br>
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        clearCurrUser(request);
        return "redirect:/";
    }

    /**
     * @Title: register<br>
     * @Description: 注册<br>
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

}
