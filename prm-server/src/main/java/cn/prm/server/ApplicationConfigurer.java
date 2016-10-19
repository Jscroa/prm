package cn.prm.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title: ApplicationConfigurer.java
 * @Package: cn.prm.server
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:39:52
 * @version v1.0
 */
@Configuration
public class ApplicationConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/error")
				.excludePathPatterns("/api/user/login").excludePathPatterns("/api/user/logout")
				.excludePathPatterns("/api/user/register").excludePathPatterns("/login").excludePathPatterns("/logout")
				.excludePathPatterns("/register");
		super.addInterceptors(registry);
	}
}
