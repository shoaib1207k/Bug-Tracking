package com.cg.bugtracking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BugTrackingApplication {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private static final Logger LOG = LogManager.getLogger(BugTrackingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BugTrackingApplication.class, args);
		LOG.info("-- Bug Tracking Application started --");
	}

}
