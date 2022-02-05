package com.devsuperior.bds04.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	public List<EventDTO> findAll(){
		List<Event> list = repository.findAll();
		return createDtoFromEntity(list);
	}
	
	private List<EventDTO> createDtoFromEntity(List<Event> entities){
		List<EventDTO> dtos = new ArrayList<>();
		for(int x = 0; x<entities.size(); x++) {
			dtos.add(new EventDTO(entities.get(x)));
		}
		return dtos;
	}
	
}
