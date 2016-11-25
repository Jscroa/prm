/**
 * 
 */
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
 * @Title: ApiLoginInterceptor.java<br>
 * @Package: cn.prm.server<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月4日 上午10:07:44<br>
 * @version v1.0<br>
 */
public class ApiLoginInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ApiLoginInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        CurrUser currUser = null;
        if (session != null) {
            currUser = (CurrUser) session.getAttribute(SESSION.LOGIN_USER);
        }
        if (currUser == null) {
            response.sendRedirect("/error/api/noLogin");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
