package cn.prm.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.SESSION;

public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession(false);
		CurrUser currUser = null;
		if (session != null) {
			currUser = (CurrUser) session.getAttribute(SESSION.LOGIN_USER);
		}
		if (currUser == null) {
			response.sendRedirect("/login");
			log.info(">>> LoginInterceptor >>> false");
			return false;
		} else {
			log.info(">>> LoginInterceptor >>> true");
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

}
