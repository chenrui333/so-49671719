package org.home.spring.mvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(excludeFilters = {@Filter(type = ANNOTATION,
                                         value = EnableWebMvc.class)},
               basePackages = "org.home.spring.mvc.common")
public class RootConfiguration {

}
