package cn.prm.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrmServerApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PrmServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PrmServerApplication.class, args);
		log.info("Application run OK!!!");
	}
}
