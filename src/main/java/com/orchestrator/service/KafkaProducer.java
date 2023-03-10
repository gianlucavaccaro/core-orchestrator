package com.orchestrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void send(String kafkaTopic, String message) {
	    kafkaTemplate.send(kafkaTopic, message);
	}

}
