package com.italk.config;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.italk.utils.Constants;

public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		if (request instanceof ServletServerHttpRequest) {
		   ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		   HttpSession session = servletRequest.getServletRequest().getSession(false);
System.out.println("sessionId: " + session.getId());
		   if (session == null || session.getAttribute(Constants.SESSION_LOGIN_USERNAME) == null) {
			   return false;
		   }
		   System.out.println(Constants.SESSION_LOGIN_USERNAME+": " + session.getAttribute(Constants.SESSION_LOGIN_USERNAME));
		 }

		// TODO Auto-generated method stub
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
