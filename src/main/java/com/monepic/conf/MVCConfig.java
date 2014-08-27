package com.monepic.conf;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.monepic"})
public class MVCConfig {
	
	Logger log = LoggerFactory.getLogger(getClass());
	@PostConstruct public void init() {
		
		log.info("LOG INFO");
		log.debug("LOG DEBUG");
		log.trace("LOG TRACE");
	}

	   
}