package cn.prm.server.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.SESSION;
import cn.prm.server.exception.SessionException;

public abstract class BaseController {
	
	
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 清除登录信息
	 * @param request
	 */
	protected void clearCurrUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		CurrUser currUser = null;
		try {
			currUser = getCurrUser(request);
		} catch (SessionException e) {
		}
		if(currUser!=null){
			session.removeAttribute(SESSION.LOGIN_USER);
			log.info("用户："+currUser.getName()+"("+currUser.getGuid()+") 已注销");			
		}
	}
	
	/**
	 * 设置当前用户
	 * @param request
	 * @param currUser
	 */
	protected void setCurrUser(HttpServletRequest request,CurrUser currUser){
		HttpSession session = request.getSession(true);
		if(currUser==null){
			return;
		}
		log.info("用户："+currUser.getName()+"("+currUser.getGuid()+") 已登录");
		session.setAttribute(SESSION.LOGIN_USER, currUser);
	}
	
	/**
	 * 获取当前用户
	 * @param request
	 * @return
	 * @throws SessionException
	 */
	protected CurrUser getCurrUser(HttpServletRequest request) throws SessionException{
		HttpSession session = request.getSession(false);
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
