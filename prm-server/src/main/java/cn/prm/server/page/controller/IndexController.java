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
 * @Title: IndexController.java
 * @Package: cn.prm.server.page.controller
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:41:38
 * @version v1.0
 */
@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@Autowired
	private MAVHelper mavHelper;

	/**
	 * 
	 * @Title: index 
	 * @Description: 主页
	 * @param request
	 * @return
	 * @throws
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
	 * 
	 * @Title: login 
	 * @Description: 登陆
	 * @return
	 * @throws
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 
	 * @Title: logout 
	 * @Description: 注销
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		clearCurrUser(request);
		return "redirect:/";
	}

	/**
	 * 
	 * @Title: register 
	 * @Description: 注册
	 * @return
	 * @throws
	 */
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

}
