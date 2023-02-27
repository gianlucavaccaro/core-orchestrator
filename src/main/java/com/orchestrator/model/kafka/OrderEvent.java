package com.orchestrator.model.kafka;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<TrackingEvent> tracking;

	public List<TrackingEvent> getTracking() {
		if(tracking == null) {
			tracking = new ArrayList<>();
		}
		return tracking;
	}

	public void setTracking(List<TrackingEvent> tracking) {
		this.tracking = tracking;
	}
	
	public TrackingEvent getLastTracking() {
		if(tracking == null) {
			tracking = new ArrayList<>();
		}
		
		if(tracking.isEmpty()) {
			return new TrackingEvent();
		}
		
		return tracking.get(tracking.size()-1);
		
	}
	
}
