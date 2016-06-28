package com.italk.repository;

import java.util.List;
import java.util.Stack;

public class MessageRepository {
	private static MessageRepository msgResp;
	
	//private static List<String> msgRepository = new ArrayList<String>();
	private Stack<String> msgRepository = new Stack<String>();
	
	private MessageRepository() {}
	
	public static MessageRepository getInstance() {
		if(msgResp == null) {
			msgResp = new MessageRepository();
		}
		return msgResp;
	}
	
	public void add(String message) {
		msgRepository.add(message);
	}
	
	public List<String> getAllMsg() {
		return msgRepository;
	}
}
