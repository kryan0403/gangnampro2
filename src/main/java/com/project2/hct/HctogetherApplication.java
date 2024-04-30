package com.project2.hct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HctogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(HctogetherApplication.class, args);
	}

}
