package com.orchestrator.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.model.kafka.OrderEvent;
import com.orchestrator.util.OrchestratorMap;

import jakarta.annotation.PostConstruct;
/*
 * Controlla il forkflow e 
 * 
 * */
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
	
	/*public OrderEvent createOrder(OrderEvent order) {
		return kafkaProducer.send(order.getLastTracking(), null);
	}*/
	
	public void sendToNextHop(OrderEvent event) throws JsonProcessingException {
		
		String kafkaTopic = orchestratorMap.getTopicName(event.getLastTracking());
		
		String json = objectMapper.writeValueAsString(event);
		
		kafkaProducer.send(kafkaTopic, json);
		
	}
	

}
