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

/**
 * @Title: LoginInterceptor.java<br>
 * @Package: cn.prm.server<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:39:55<br>
 * @version v1.0<br>
 */
public class LoginInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
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
            return false;
        }
        else {
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
