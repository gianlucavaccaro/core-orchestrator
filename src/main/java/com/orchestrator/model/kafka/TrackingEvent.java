package com.orchestrator.model.kafka;

import java.io.Serializable;

public class TrackingEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String serviceName;
	private String identifier;
	private String identifierType;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	
	
}
