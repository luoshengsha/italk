package com.italk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.italk.websocket.MyHandshakeInterceptor;
import com.italk.websocket.MyWebSocketHandler;

//@Configuration
//@EnableWebSocketMessageBroker
/*public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
	}

}*/

public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(),"/echo").addInterceptors(new MyHandshakeInterceptor()); //支持websocket 的访问链接
        registry.addHandler(new MyWebSocketHandler(),"/sockjs/echo").addInterceptors(new MyHandshakeInterceptor()).withSockJS(); //不支持websocket的访问链接
	}
	
}
