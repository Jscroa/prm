package cn.prm.server.service;

import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.form.UserLoginForm;
import cn.prm.server.form.UserRegisterForm;

@Service(value="userService")
public class UserService {

	public CurrUser login(UserLoginForm form){
		CurrUser user = new CurrUser();
		user.setGuid("My Guid");
		user.setName("My Name");
		return user;
	}
	
	public CurrUser register(UserRegisterForm form) {
		CurrUser user = new CurrUser();
		user.setGuid("My Guid");
		user.setName("My Name");
		return user;
	}
	
}
