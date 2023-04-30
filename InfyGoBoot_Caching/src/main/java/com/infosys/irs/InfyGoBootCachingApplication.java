package com.infosys.irs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
@EnableCaching
@SpringBootApplication
@PropertySource(value = {"classpath:configuration.properties"})
public class InfyGoBootCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfyGoBootCachingApplication.class, args);
	}
}
