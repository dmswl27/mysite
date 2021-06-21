package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//	<context-param>
//		<param-name>contextConfigLocation</param-name>
//		<param-value>com.douzone.mysite.config.AppConfig</param-value>
//	</context-param>
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context's Configuration Class
		return new Class<?>[] {AppConfig.class};
	}

//	<servlet>
//		<servlet-name>spring</servlet-name>
//		<servlet-class>org.springframework.web.servlet.DispatcherServlet
//		</servlet-class>
//		<servlet-name>spring</servlet-name>
//		<servlet-class>org.springframework.web.servlet.DispatcherServlet
//		</servlet-class>
//		<init-param>
//			<param-name>contextClass</param-name>
//			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext
//			</param-value>
//		</init-param>
//		<init-param>
//			<param-name>contextConfigLocation</param-name>
//			<param-value>com.douzone.mysite.config.WebConfig</param-value>
//		</init-param>
//	</servlet>

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context's Configuration Class
		return new Class<?>[] {WebConfig.class};
	}

//	<servlet-mapping>
//		<servlet-name>spring</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>

	@Override
	protected String[] getServletMappings() {
		// Dispatcher Servlet Mapping URL
		return new String[] {"/"};
	}

//	<filter>
//		<filter-name>encodingFilter</filter-name>
//		<filter-class>org.springframework.web.filter.CharacterEncodingFilter
//	</filter-class>
//	<init-param>
//		<param-name>encoding</param-name>
//		<param-value>UTF-8</param-value>
//	</init-param>
//	</filter>
//	<filter-mapping>
//		<filter-name>encodingFilter</filter-name>
//		<url-pattern>/*</url-pattern>
//	</filter-mapping>
	
	@Override
	protected Filter[] getServletFilters() {
		// Encoding Filter
		return new Filter[] {new CharacterEncodingFilter("UTF-8",false)};
	}
	
	

}
