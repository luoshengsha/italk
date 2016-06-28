package com.italk.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italk.service.UserService;
import com.italk.service.UserTokenService;
import com.italk.utils.Constants;
import com.italk.vo.ReturnObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * token校验配置
 * @author luoshengsha
 *
 * 2016年6月21日-下午6:28:37
 */
@Configuration
public class TokenConfig extends WebMvcConfigurerAdapter {
	
	@Resource
	private UserService userService;
	@Resource
	private UserTokenService tokenService;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new tokenSecurityInterceptor()).addPathPatterns("/app/**","/center/**");
		super.addInterceptors(registry);
	}
	
	/**
	 * 校验token拦截器
	 * @author luoshengsha
	 *
	 * 2016年6月21日-下午6:48:47
	 */
	class tokenSecurityInterceptor implements HandlerInterceptor {
		/** 日志记录 **/
		private final Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			ReturnObject obj = new ReturnObject();
			
			final String token = request.getParameter("token");
			if(StringUtils.isEmpty(token)) {
				obj.setStatus(0);
				obj.setMessage("token is losed");
				
				//输出json格式提示
				printJson(response, obj);
				
				return false;
				
			} else if(!tokenService.isExist(token)) {
				obj.setStatus(0);
				obj.setMessage("token is invalid");
				
				//输出json格式提示
				printJson(response, obj);
				
				return false;
			}
			
			try {
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				String name = claims.getSubject();
				String password = (String) claims.get("pw");

				if(userService.checkUser(name, password)) {
					request.setAttribute(Constants.SESSION_LOGIN_USERNAME, userService.getByName(name));
					return true;
				} else {
					obj.setStatus(0);
					obj.setMessage("token is invalid");
					
					//输出json格式提示
					printJson(response, obj);
					
					return false;
				}
			} catch (Exception e) {
				obj.setStatus(0);
				obj.setMessage("occur error when check token...");
				
				//输出json格式提示
				printJson(response, obj);
				
				logger.error("校验token失败", e);
				return false;
			}
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
			// TODO Auto-generated method stub
			
		}
		

		/**
		 * 输出json
		 * @param response
		 * @param obj
		 * @throws JsonProcessingException
		 * @throws IOException
		 */
		private void printJson(HttpServletResponse response, ReturnObject obj)
				throws JsonProcessingException, IOException {
			response.setContentType("application/json; charset=utf-8");
			ObjectMapper m = new ObjectMapper();  
			String json = m.writeValueAsString(obj);  
			response.getWriter().print(json);
		}
		
	}

}
