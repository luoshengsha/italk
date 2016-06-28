package com.italk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//config.enableSimpleBroker("/chat/p2p","/chat/group");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS().setInterceptors(new WebSocketHandshakeInterceptor());
		registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}

	@Override
	  public void configureClientInboundChannel(ChannelRegistration registration) {
	    registration.setInterceptors(new WebsocketInterceptor());
	  }
}
