package cn.prm.server.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	@Override
	@RequestMapping(value = "/error")
	public String getErrorPath() {
		return "error/404";
	}
}
