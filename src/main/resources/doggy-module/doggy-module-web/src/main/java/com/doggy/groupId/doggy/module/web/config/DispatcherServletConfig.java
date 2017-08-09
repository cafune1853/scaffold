package com.doggy.groupId.doggy.module.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
@Configuration
@ComponentScan(basePackages = {"com.doggy.groupId.doggy.module.web.controller"})
@EnableWebMvc
public class DispatcherServletConfig extends WebMvcConfigurerAdapter{
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
        jspResolver.setPrefix("/WEB-INF/jsp/");
        jspResolver.setSuffix(".jsp");
        jspResolver.setExposeContextBeansAsAttributes(true);
        return jspResolver;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
