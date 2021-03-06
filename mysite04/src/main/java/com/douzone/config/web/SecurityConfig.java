package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.mysite.security.AuthInterceptor;
import com.douzone.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.mysite.security.LoginInterceptor;
import com.douzone.mysite.security.LogoutInterceptor;

@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {
	/*
	 * <!-- Validator, conversionService, messageConverter를 자동으로 등록 -->
	 * <mvc:annotation-driven>
	 * 
	 * <mvc:argument-resolvers> <bean
	 *	 class="com.douzone.mysite.security.AuthUserHandlerMethodArgumentResolver" />
	 * </mvc:argument-resolvers> </mvc:annotation-driven>
	 * 
	 * 
	 * <!-- Interceptors --> 
	 * <mvc:interceptors> 
	 * 	<mvc:interceptor> 
	 * 		<mvc:mapping path="/user/auth" /> 
	 * 			<bean class="com.douzone.mysite.security.LoginInterceptor" /> 
	 * 		</mvc:interceptor>
	 * 	<mvc:interceptor> 
	 * 	<mvc:mapping path="/user/logout" /> 
	 * 		<bean class="com.douzone.mysite.security.LogoutInterceptor" /> 
	 * 	</mvc:interceptor>
	 * 	<mvc:interceptor> 
	 * 		<mvc:mapping path="/**" /> 
	 * 		<mvc:exclude-mapping path="/user/auth" /> 
	 * 		<mvc:exclude-mapping path="/user/logout" />
	 * 		<mvc:exclude-mapping path="/assets/**" /> 
	 * 		<bean class="com.douzone.mysite.security.AuthInterceptor" /> 
	 * 	</mvc:interceptor>
	 * </mvc:interceptors>
	 * 
	 * 
	 */
	// Argument Resolver
		@Bean
		public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
			return new AuthUserHandlerMethodArgumentResolver();
		}

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			argumentResolvers.add(handlerMethodArgumentResolver());
		}
		
		// Interceptors
		@Bean
		public HandlerInterceptor loginInterceptor() {
			return new LoginInterceptor();
		}

		@Bean
		public HandlerInterceptor logoutInterceptor() {
			return new LogoutInterceptor();
		}

		@Bean
		public HandlerInterceptor authInterceptor() {
			return new AuthInterceptor();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry
				.addInterceptor(loginInterceptor())
				.addPathPatterns("/user/auth");
			
			registry
				.addInterceptor(logoutInterceptor())
				.addPathPatterns("/user/logout");
			
			registry
				.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/auth")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/assets/**");
		}

}
