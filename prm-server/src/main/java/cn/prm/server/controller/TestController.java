package cn.prm.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@RequestMapping(value="/a")
	public String a(){
		return "test/a";
	}
	@RequestMapping(value="/b")
	@ResponseBody
	public String b(){
		return "test/b";
	}
}
