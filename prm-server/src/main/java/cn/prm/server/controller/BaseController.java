package cn.prm.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.SESSION;
import cn.prm.server.exception.SessionException;

public abstract class BaseController {

	protected void setCurrUser(HttpServletRequest request,CurrUser currUser){
		HttpSession session = request.getSession(true);
		if(currUser==null){
			return;
		}
		session.setAttribute(SESSION.LOGIN_USER, currUser);
	}
	
	protected CurrUser getCurrUser(HttpServletRequest request) throws SessionException{
		HttpSession session = request.getSession(true);
		CurrUser currUser = null;
		if(session!=null){
			currUser = (CurrUser) session.getAttribute(SESSION.LOGIN_USER);
		}
		if(currUser==null){
			throw new SessionException("未获取到登录信息");
		}
		return currUser;
	}
}
