package com.italk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

/**
 * 跨域访问 response返回设置过滤器
 * @author mhz
 *
 */
@ServletComponentScan
@WebFilter(filterName="CORSFilter",urlPatterns="/*")
@Component
public class SimpleCORSFilter implements Filter{

	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		    HttpServletResponse response = (HttpServletResponse) res;
		    response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers", "*");
		    /*
		    HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String clientOrigin = request.getHeader("origin");
	        response.addHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET,  DELETE, PUT");
	        //response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Accept, Content-Type, Origin, Authorization, X-Auth-Token");
	        //response.addHeader("Access-Control-Expose-Headers", "X-Auth-Token");*/
		    
		    chain.doFilter(req, res);
		  }
		  public void init(FilterConfig filterConfig) {}
		  public void destroy() {}
}

