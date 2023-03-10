package com.orchestrator.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orchestrator.model.OrderData;
import com.orchestrator.model.kafka.OrderEvent;
import com.orchestrator.model.kafka.TrackingEvent;
import com.orchestrator.service.OrchestratorService;

@RestController
@RequestMapping("/orchestrator")
public class OrchestratorController {
	
	@Autowired
	OrchestratorService orchestratorService;

	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrderData order) throws JsonProcessingException {
		OrderEvent event=new OrderEvent();
		TrackingEvent tracking= new TrackingEvent();
		event.setIdProdotto(order.getIdProdotto());
		event.setNumeroPezzi(order.getNumeroPezzi());
		event.setIdMagazzino(order.getIdMagazzino());
		String uuid = UUID.randomUUID().toString();
		event.setUUID_str(uuid);
		event.getTracking().add(tracking);
		orchestratorService.sendToNextHop(event);
		System.out.println("Messaggio inviato: "+ event.getLastTracking().toString());
		return new ResponseEntity<String>("Ordine preso in carico",HttpStatus.OK);
	}
	
}
