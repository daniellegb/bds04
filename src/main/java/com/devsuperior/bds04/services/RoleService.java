package com.devsuperior.bds04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.RoleDTO;
import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public List<RoleDTO> findAll(){
		List<Role> list = repository.findAll();
		return createDtoFromEntity(list);
	}
	
	private List<RoleDTO> createDtoFromEntity(List<Role> entities){
		List<RoleDTO> dtos = new ArrayList<>();
		for(int x = 0; x<entities.size(); x++) {
			dtos.add(new RoleDTO(entities.get(x)));
		}
		return dtos;
	}
	
}
