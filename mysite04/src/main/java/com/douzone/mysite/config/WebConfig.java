package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileuploadConfig;
import com.douzone.config.web.MesaageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

// <context:annotation-config />
// <context:component-scan base-package="com.douzone.mysite.controller, com.douzone.mysite.exception" />

@Configuration
@ComponentScan({"com.douzone.mysite.controller, com.douzone.mysite.exception"})
@EnableAspectJAutoProxy
@Import({MvcConfig.class,MesaageConfig.class, FileuploadConfig.class,SecurityConfig.class})
public class WebConfig {

}
