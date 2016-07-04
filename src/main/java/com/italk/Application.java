package com.italk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.italk.utils.PropertiesUtil;

@SpringBootApplication
@EnableConfigurationProperties({PropertiesUtil.class})
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
    
    @Override    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {    
        return builder.sources(Application.class);
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
