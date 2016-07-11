package cn.prm.server.commons;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.commons.Constants.MAV_KEYS;

@Component
public class MAVHelper {

	public ModelAndView withUserName(ModelAndView mav, Object value) {
		return mav.addObject(MAV_KEYS.USER_NAME, value);
	}

}
