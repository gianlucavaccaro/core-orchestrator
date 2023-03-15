package com.orchestrator.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
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
	@Autowired
	KafkaListenerEndpointRegistry registry;
	
	private ObjectMapper objectMapper;
	private static final Logger logger = LogManager.getLogger(KafkaConsumer.class);
	
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

	@KafkaListener(id="orchestrator",topics = "TOPIC_ORCHESTRATOR_IN", groupId = "CORE-ORCHESTRATOR_KAFKA_TOPIC_ORCHESTRATOR_IN")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("message = " + message);
		OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
		logger.info("Received OrdeEvent in core-orch from "+ event.getLastTracking().getServiceName() + ", with status - "+event.getLastTracking().getStatus()+".");
		/*TrackingEvent trackingRecord=new TrackingEvent();
		trackingRecord=event.getLastTracking();
		if(trackingRecord.getServiceName().equals("core-order") && trackingRecord.getStatus().equals("CONFIRMED"))
			registry.getListenerContainer("orchestrator").stop();
		else*/
			service.sendToNextHop(event);
		logger.info("Sending EventOrder from core-orchestrator to next step.");
	}

}
