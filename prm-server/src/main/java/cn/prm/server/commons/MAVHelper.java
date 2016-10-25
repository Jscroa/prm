package cn.prm.server.commons;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.commons.Constants.MAV_KEYS;

/**
 * @Title: MAVHelper.java<br>
 * @Package: cn.prm.server.commons<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:28:32<br>
 * @version v1.0<br>
 */
@Component
public class MAVHelper {

    /**
     * @Title: withUserName<br>
     * @Description: <br>
     * @param mav
     * @param value
     * @return
     */
    public ModelAndView withUserName(ModelAndView mav, Object value) {
        return mav.addObject(MAV_KEYS.USER_NAME, value);
    }

}
