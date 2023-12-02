package com.socket.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socket.demo.entity.ChatMessage;

@RestController
@CrossOrigin("*")
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/public")    // sends message to that topic
	public ResponseEntity<ChatMessage> sendMessage(@Payload ChatMessage chatMessage){
		
		return ResponseEntity.ok(chatMessage);
	}
	
	public ChatMessage addUser(@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor headerAccesser) {
		 // add user name in web socket session
		headerAccesser.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
		
	}
}
