package cn.prm.server.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.annotation.NeedLogin;
import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.exception.SessionException;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@RequestMapping({ "", "/index" })
	@NeedLogin(value="asdasdasdasd")
	public String index(HttpServletRequest request) {
		try {
			CurrUser currUser = getCurrUser(request);
		} catch (SessionException e) {
		return "login";
		}
		return "index";
	}

	@RequestMapping("/login")
	public String login(String ticket) {
		return "login";
	}

	@RequestMapping("/head")
	public ModelAndView head(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("nav");
		try {
			CurrUser currUser = getCurrUser(request);
			mav.addObject("userName", currUser.getName());
		} catch (SessionException e) {
			
		}
		return mav;
	}

}
