package com.orchestrator.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.model.kafka.OrderEvent;
import com.orchestrator.model.kafka.TrackingEvent;

import jakarta.annotation.PostConstruct;

@Component
public class KafkaConsumer {
	
	@Autowired
	OrchestratorService service; 
	
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void init() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

//nuovo listener per gestire la notifica?!

	@KafkaListener(topics = "TOPIC_ORCHESTRATOR_IN", groupId = "CORE-ORCHESTRATOR_KAFKA_TOPIC_ORCHESTRATOR_IN")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("message = " + message);
		OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
		TrackingEvent trackingRecord=new TrackingEvent();
		trackingRecord=event.getLastTracking();
		if(trackingRecord.getStatus().equals("CANCELLED"))
			System.out.println(trackingRecord.getFailureReason());
		else if (trackingRecord.getStatus().equals("REJECTED"))
			System.out.println(trackingRecord.getFailureReason());
		else
			service.sendToNextHop(event);
		System.out.println("Messaggio da orchestrator inviato");
	}

}
