package com.italk.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class WebsocketInterceptor extends ChannelInterceptorAdapter {

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
	    StompCommand command = accessor.getCommand();
	    
		return super.preSend(message, channel);
	}

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub
		super.postSend(message, channel, sent);
	}

	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		// TODO Auto-generated method stub
		super.afterSendCompletion(message, channel, sent, ex);
	}

	@Override
	public boolean preReceive(MessageChannel channel) {
		// TODO Auto-generated method stub
		return super.preReceive(channel);
	}

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		// TODO Auto-generated method stub
		return super.postReceive(message, channel);
	}

	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		// TODO Auto-generated method stub
		super.afterReceiveCompletion(message, channel, ex);
	}

}
