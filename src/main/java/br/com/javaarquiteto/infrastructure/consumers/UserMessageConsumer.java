package br.com.javaarquiteto.infrastructure.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.javaarquiteto.application.dtos.UserMessageDto;
import br.com.javaarquiteto.infrastructure.components.MailSenderComponent;
import jakarta.mail.MessagingException;

@Service
public class UserMessageConsumer {
	
	@Autowired
	MailSenderComponent mailSenderComponent;
	@Autowired
	ObjectMapper objectMapper;
	
	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String message) {
		
		try {
			UserMessageDto dto = objectMapper.readValue(message, UserMessageDto.class);
			
			mailSenderComponent.sendMessage(dto.getEmailTo(), dto.getSubject(), dto.getBody());
			
		} catch (JsonProcessingException | MessagingException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
