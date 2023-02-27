package com.orchestrator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic productIn() {
		return TopicBuilder.name("TOPIC_PRODUCT_IN").build();
	}
	
	@Bean
	public NewTopic productOut() {
		return TopicBuilder.name("TOPIC_PRODUCT_OUT").build();
	}
	
	@Bean
	public NewTopic storageIn() {
		return TopicBuilder.name("TOPIC_STORAGE_IN").build();
	}
	
	@Bean
	public NewTopic storageOut() {
		return TopicBuilder.name("TOPIC_STORAGE_OUT").build();
	}
	
	@Bean
	public NewTopic orderIn() {
		return TopicBuilder.name("TOPIC_ORDER_IN").build();
	}
	
	@Bean
	public NewTopic orderOut() {
		return TopicBuilder.name("TOPIC_ORDER_OUT").build();
	}
	
}
