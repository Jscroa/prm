package cn.prm.server.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.annotation.NeedLogin;
import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.MAVHelper;
import cn.prm.server.exception.SessionException;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@Autowired
	private MAVHelper mavHelper;
	
	/**
	 * 主页
	 * @param request
	 * @return
	 */
	@RequestMapping({ "", "/index" })
	@NeedLogin(value="asdasdasdasd")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			CurrUser currUser = getCurrUser(request);
			mav.setViewName("index");
			mavHelper.with(mav, "userName", currUser.getName());
			return mav;
		} catch (SessionException e) {
			mav.setViewName("login");
			return mav;
		}
	}

	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		clearCurrUser(request);
		return "redirect:/";
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/register")
	public String register(){
		return "register";
	}

}
