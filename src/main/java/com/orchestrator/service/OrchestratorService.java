package com.orchestrator.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.model.kafka.OrderEvent;
import com.orchestrator.util.OrchestratorMap;

@Service
public class OrchestratorService {
	
	@Autowired
	private OrchestratorMap orchestratorMap;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void init() {
		objectMapper = new ObjectMapper();
	}
	
	public void sendToNextHop(OrderEvent event) throws JsonProcessingException {
		
		String kafkaTopic = orchestratorMap.getTopicName(event.getLastTracking());
		
		String json = objectMapper.writeValueAsString(event);
		
		kafkaProducer.send(kafkaTopic, json);
		
	}
	

}
