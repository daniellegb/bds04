package com.devsuperior.bds04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> list = repository.findAll();
		return createDtoFromEntity(list);
	}
	
	private List<CityDTO> createDtoFromEntity(List<City> entities){
		List<CityDTO> dtos = new ArrayList<>();
		for(int x = 0; x<entities.size(); x++) {
			dtos.add(new CityDTO(entities.get(x)));
		}
		return dtos;
	}

	public Page<CityDTO> findAllPaged(PageRequest pageRequest) {
		Page<City> list =repository.findAll(pageRequest);
		return list.map(x -> new CityDTO(x));
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City(dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
	}

}
