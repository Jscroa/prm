package cn.prm.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title: ApplicationConfigurer.java<br>
 * @Package: cn.prm.server<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:39:52<br>
 * @version v1.0<br>
 */
@Configuration
public class ApplicationConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置页面接口相关的拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/error/**")
                .excludePathPatterns("/login").excludePathPatterns("/logout").excludePathPatterns("/register")
                .excludePathPatterns("/custom/customAddress").excludePathPatterns("/fragment/**").excludePathPatterns("/api/**");
        // 有部分接口介于页面和json数据之间（返回页面，但不能重定向到登录页，某些内嵌页面用到），这个怎么做还有待商榷，暂定不经过拦截器
        // 配置json数据相关的拦截器
        registry.addInterceptor(new ApiLoginInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login").excludePathPatterns("/api/user/logout")
                .excludePathPatterns("/api/user/register");
        super.addInterceptors(registry);
    }
}
