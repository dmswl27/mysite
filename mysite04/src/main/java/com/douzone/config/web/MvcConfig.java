package com.douzone.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
	
//	<!-- ViewResolver 설정 -->
//	<bean id="viewResolver"
//		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//		<property name="viewClass"
//			value="org.springframework.web.servlet.view.JstlView" />
//		<property name="prefix" value="/WEB-INF/views/" />
//		<property name="suffix" value=".jsp" />
//	</bean>

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		
		return viewResolver;
	}
	
//	<!-- Validator, conversionService, messageConverter를 자동으로 등록 -->
//	<mvc:annotation-driven>
//		<mvc:message-converters>
//			<bean
//				class="org.springframework.http.converter.StringHttpMessageConverter">
//				<property name="supportedMediaTypes">
//					<list>
//						<value>text/html; charset=UTF-8</value>
//					</list>
//				</property>
//			</bean>
//
//		</mvc:message-converters>
//	</mvc:annotation-driven>
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(
		            Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));
		return messageConverter;
	}
	
//	<bean
//		class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
//		<property name="supportedMediaTypes">
//			<list>
//				<value>application/json; charset=UTF-8</value>
//			</list>
//		</property>
//	</bean>
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder
			.indentOutput(true)
			.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(
				Arrays.asList(new MediaType("application", "json", Charset.forName("utf-8")))
				);
		return messageConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}

	//Deflault Servlet Handler 등록 작업
//	<!-- 서블릿 컨테이너의 디폴트 서블릿 위임 핸들러 -->
//	<mvc:default-servlet-handler />

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	

	
	
	

}
