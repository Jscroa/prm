package cn.prm.server.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MAVHelper {

	public ModelAndView with(ModelAndView mav, String key, Object value){
		return mav.addObject(key, value);
	}
	
	public ModelAndView withNav(ModelAndView mav){
		
		List<String> navs = new ArrayList<>();
		navs.add("AAAAAAAA");
		navs.add("BBBBBBBB");
		return mav.addObject("navs", navs);
	}
}
