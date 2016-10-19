package cn.prm.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.prm.server.commons.UUIDUtil;

/**
 * 
 * @Title: PrmServerApplicationTests.java
 * @Package: cn.prm.server
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午4:56:03
 * @version v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrmServerApplication.class)
@WebAppConfiguration
public class PrmServerApplicationTests {

	/**
	 * 
	 * @Title: contextLoads 
	 * @Description: 
	 * @param 
	 * @return void
	 * @throws
	 */
	@Test
	public void contextLoads() {
		
	}

}
