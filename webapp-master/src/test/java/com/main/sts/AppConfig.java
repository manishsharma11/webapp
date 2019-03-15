package com.main.sts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}
