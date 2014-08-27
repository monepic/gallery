package com.monepic.conf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@ComponentScan(basePackages = {"com.monepic"})
public class MVCConfig extends WebMvcConfigurerAdapter {
	
	Logger log = LoggerFactory.getLogger(getClass());
	@PostConstruct public void init() {
		
		log.info("LOG INFO");
		log.debug("LOG DEBUG");
		log.trace("LOG TRACE");
	}
	
	

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
    }
 
    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrefixJson(false);
        converter.setPrettyPrint(true);
   //     converter.setObjectMapper(objectMapper());
        return converter;
    }

	   
}