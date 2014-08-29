package com.monepic.gallery.conf;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@EnableEntityLinks
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@ComponentScan(basePackages = {"com.monepic"})
public class MVCConfig extends WebMvcConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) { }

}