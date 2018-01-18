package com.dituhui.saas.alan.a.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.dituhui.saas.alan.a.in" })
public class SkinPushApplication {
	
	public static void main(String[] args) {
		String[] args2 = new String[]{"--server.port=8806","--spring.profiles.active=ain1"};
		SpringApplication.run(SkinPushApplication.class, args2);
	}

}
