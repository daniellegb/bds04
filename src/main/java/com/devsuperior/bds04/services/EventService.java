package com.devsuperior.bds04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

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
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event(dto);
		entity = repository.save(entity);
		return new EventDTO(entity);
	}

	public Page<EventDTO> findAllPaged(PageRequest pageRequest) {
		Page<Event> list =repository.findAll(pageRequest);
		return list.map(x -> new EventDTO(x));
	}
	
}
