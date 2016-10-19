package cn.prm.server.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: ErrorController.java
 * @Package: cn.prm.server.page.controller
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:41:35
 * @version v1.0
 */
@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	@Override
	@RequestMapping(value = "/error")
	public String getErrorPath() {
		return "error/404";
	}
}
