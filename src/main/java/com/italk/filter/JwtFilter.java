package com.italk.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.italk.service.UserService;
import com.italk.service.UserTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

//@WebFilter(value="/center/*")
public class JwtFilter extends GenericFilterBean {
	
	@Resource
	private UserService userService;
	@Resource
	private UserTokenService tokenService;

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;

		/*
		 * final String authHeader = request.getHeader("Authorization"); if
		 * (authHeader == null || !authHeader.startsWith("Bearer ")) { throw new
		 * ServletException("Missing or invalid Authorization header."); }
		 * 
		 * final String token = authHeader.substring(7); // The part after
		 * "Bearer "
		 */
		final String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			throw new ServletException("token is losed");
		} else if(!tokenService.isExist(token)) {
			throw new ServletException("token is invalid");
		}
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();

			String name = claims.getSubject();
			String password = (String) claims.get("pw");

			if(userService.checkUser(name, password)) {
				request.setAttribute("loginUser", userService.getByName(name));
				chain.doFilter(req, res);
			} else {
				throw new ServletException("Invalid token.");
			}

		} catch (final SignatureException e) {
			throw new ServletException("Invalid token.");
		} catch (MalformedJwtException e) {
			throw new ServletException("Invalid token");
		}

	}

}
