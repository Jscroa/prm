package cn.prm.server.commons;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MAVHelper {

	public ModelAndView with(ModelAndView mav, String key, Object value){
		return mav.addObject(key, value);
	}
}
