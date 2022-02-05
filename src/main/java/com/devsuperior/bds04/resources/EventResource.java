package com.devsuperior.bds04.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

	@Autowired
	private EventService service;
	
	@GetMapping
	public ResponseEntity<List<EventDTO>> findAll(){
		List<EventDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
