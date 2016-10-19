package cn.prm.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @Title: ServletInitializer.java
 * @Package: cn.prm.server
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:40:09
 * @version v1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PrmServerApplication.class);
	}

}
