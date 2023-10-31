package com.swisscom.aiops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class AiOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiOpsApplication.class, args);
	}

}
