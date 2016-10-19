package cn.prm.server.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.SESSION;

/**
 * 
 * @Title: BaseController.java
 * @Package: cn.prm.server.commons
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:21:48
 * @version v1.0
 */
public abstract class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 
	 * @Title: clearCurrUser 
	 * @Description: 清除登录信息
	 * @param request
	 * @throws
	 */
	protected void clearCurrUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CurrUser currUser = getCurrUser(request);
		if (currUser != null) {
			session.removeAttribute(SESSION.LOGIN_USER);
			log.info("用户：" + currUser.getName() + "(" + currUser.getGuid() + ") 已注销");
		}
	}

	/**
	 * 
	 * @Title: setCurrUser 
	 * @Description: 设置当前用户
	 * @param request
	 * @param currUser
	 * @throws
	 */
	protected void setCurrUser(HttpServletRequest request, CurrUser currUser) {
		HttpSession session = request.getSession(true);
		if (currUser == null) {
			return;
		}
		log.info("用户：" + currUser.getName() + "(" + currUser.getGuid() + ") 正在登陆");
		session.setAttribute(SESSION.LOGIN_USER, currUser);
	}

	/**
	 * 
	 * @Title: getCurrUser 
	 * @Description: 获取当前用户
	 * @param request
	 * @return
	 * @throws
	 */
	protected CurrUser getCurrUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		CurrUser currUser = null;
		if (session != null) {
			currUser = (CurrUser) session.getAttribute(SESSION.LOGIN_USER);
		}
		return currUser;
	}

}
