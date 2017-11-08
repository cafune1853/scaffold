package com.doggy.groupId.doggy.module.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
@Configuration
@ComponentScan(value = "com.doggy.groupId.doggy.module.web", excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { Controller.class, RestController.class }) })
@EnableAspectJAutoProxy
public class RootConfig {
}
