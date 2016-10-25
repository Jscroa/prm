package cn.prm.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @Title: ServletInitializer.java<br>
 * @Package: cn.prm.server<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:09<br>
 * @version v1.0<br>
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PrmServerApplication.class);
    }

}
