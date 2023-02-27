package com.orchestrator.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.orchestrator.model.kafka.TrackingEvent;

@Component
public class OrchestratorMap {
	
	private Map<String, String> topicMap;
	
	/*
	 * 
	 * 		«tracking»:»core-product»,
		«status»: »OK»,
		«TOPIC»: » TOPIC_STORAGE_IN»

	 * */
	
	@PostConstruct
	public void init() {
		
		topicMap = new HashMap<String, String>();
		
		// ms null e stato null -> è il primo evento -> invio verso
		topicMap.put(getKey(null, null),  "TOPIC_PRODUCT_IN");
		
		topicMap.put(getKey("core-product", "OK"),  "TOPIC_STORAGE_IN");
		
	}
	
	private String getKey(String serviceName, String status) {
		return serviceName+"-"+status;
	}
	
	public String getTopicName(TrackingEvent event) {
		return topicMap.get(getKey(event.getServiceName(), event.getStatus()));
	}

}
