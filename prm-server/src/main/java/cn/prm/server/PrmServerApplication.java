package cn.prm.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title: PrmServerApplication.java<br>
 * @Package: cn.prm.server<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:00<br>
 * @version v1.0<br>
 */
@SpringBootApplication
@EnableTransactionManagement // 启用注解管理事务
public class PrmServerApplication {

    private static final Logger log = LoggerFactory.getLogger(PrmServerApplication.class);

    /**
     * @Title: main<br>
     * @Description: <br>
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(PrmServerApplication.class, args);
        log.info("Application run OK!!!");
    }
}
