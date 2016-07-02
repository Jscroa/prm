package cn.prm.server.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.prm.server.annotation.NeedLogin;
import cn.prm.server.commons.BaseController;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@RequestMapping({ "", "/index" })
	@NeedLogin(value="asdasdasdasd")
	public String index(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/login")
	public String login(String ticket) {
		return "login";
	}
}
