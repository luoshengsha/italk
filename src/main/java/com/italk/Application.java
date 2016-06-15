package com.italk;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	/*@Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(openSessionInView());
        registration.addUrlPatterns("/*");

        return registration;
    }

    @Bean
    public Filter openSessionInView() {
        return new OpenSessionInViewFilter();
    }*/
}
