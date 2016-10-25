package cn.prm.server.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.SESSION;

/**
 * @Title: BaseController.java<br>
 * @Package: cn.prm.server.commons<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:21:48<br>
 * @version v1.0<br>
 */
public abstract class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    /**
     * @Title: clearCurrUser<br>
     * @Description: 清除登录信息<br>
     * @param request
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
     * @Title: setCurrUser<br>
     * @Description: 设置当前用户<br>
     * @param request
     * @param currUser
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
     * @Title: getCurrUser<br>
     * @Description: 获取当前用户<br>
     * @param request
     * @return
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
