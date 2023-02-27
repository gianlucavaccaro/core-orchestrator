package com.orchestrator.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.model.kafka.OrderEvent;

@Component
public class KafkaConsumer {
	
	@Autowired
	OrchestratorService service; 
	
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void init() {
		objectMapper = new ObjectMapper();
	}

	@KafkaListener(topics = "TOPIC_ORCHESTRATOR_IN", groupId = "CORE-ORCHESTRATOR_KAFKA_TOPIC_ORCHESTRATOR_IN")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		// Print statement
		System.out.println("message = " + message);
		
		OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
		
		service.sendToNextHop(event);
	}

}
