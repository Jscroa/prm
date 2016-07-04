package cn.prm.server.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;
import cn.prm.server.form.UserLoginForm;
import cn.prm.server.form.UserRegisterForm;
import cn.prm.server.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public Object login(HttpServletRequest request,UserLoginForm form){
		CurrUser user = userService.login(form);
		setCurrUser(request, user);
		
		return new BaseDto(RESPONSE_CODE.CODE_SUCCESS,"登录成功");
	}
	
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request){
		clearCurrUser(request);
		
		return new BaseDto(RESPONSE_CODE.CODE_SUCCESS,"注销成功");
	}
	
	@RequestMapping("/register")
	public Object register(HttpServletRequest request,UserRegisterForm form){
		CurrUser currUser = userService.register(form);
		setCurrUser(request, currUser);
		
		return new BaseDto(RESPONSE_CODE.CODE_SUCCESS,"注册成功");
	}
}
