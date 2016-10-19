package cn.prm.server.commons;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.commons.Constants.MAV_KEYS;

/**
 * @Title: MAVHelper.java
 * @Package: cn.prm.server.commons
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:28:32
 * @version v1.0
 */
@Component
public class MAVHelper {

	/** 
	 * @Title: withUserName 
	 * @Description: 
	 * @param mav
	 * @param value
	 * @return
	 * @throws 
	 */
	public ModelAndView withUserName(ModelAndView mav, Object value) {
		return mav.addObject(MAV_KEYS.USER_NAME, value);
	}

}
