package com.orchestrator.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.orchestrator.model.kafka.TrackingEvent;

import jakarta.annotation.PostConstruct;

@Component
public class OrchestratorMap {
	
	private Map<String, String> topicMap;
	
	/*
	 * 
	 * 	«tracking»:»core-product»,
		«status»: »OK»,
		«TOPIC»: » TOPIC_STORAGE_IN»

	 * */
	
	@PostConstruct
	public void init() {
		
		topicMap = new HashMap<String, String>();
		// ms null e stato null -> è il primo evento -> invio verso
		topicMap.put(getKey(null, null),  "TOPIC_PRODUCT_IN");
		topicMap.put(getKey("core-product", "OK"),  "TOPIC_STORAGE_IN");
		topicMap.put(getKey("core-storage","OK"), "TOPIC_ORDER_IN");
		//modificare: caso i cui in order vado KO e devo rollbackare la modifica
		topicMap.put(getKey("core-order","KO"), "TOPIC_STORAGE_ROLLBACK");
		topicMap.put(getKey("core-product", "KO"),  "TOPIC_ORDER_IN");
		topicMap.put(getKey("core-storage","KO"), "TOPIC_ORDER_IN");
		topicMap.put(getKey("core-storage","REJECTED"), "TOPIC_ORDER_IN");               
		//ritorno su order dopo aver incrementato in seguito alla transazione di compensazione
		topicMap.put(getKey("core-storage","ROLLBACK"), "TOPIC_ORDER_IN");
	}
	
	private String getKey(String serviceName, String status) {
		return serviceName+"-"+status;
	}
	
	public String getTopicName(TrackingEvent event) {
		return topicMap.get(getKey(event.getServiceName(), event.getStatus()));
	}

}
