package com.chamith.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chamith.app.ws.security.AppProperties;

@SpringBootApplication
public class WebMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebMobileApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext SpringApplicationContext()
	{
		return new SpringApplicationContext(); 
	}

	@Bean(name="AppProperties")
	public AppProperties getAppProperties()
	{
		return new AppProperties();
	}
}
