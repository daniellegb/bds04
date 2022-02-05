package com.devsuperior.bds04.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;
import com.devsuperior.bds04.dto.UserDTO;
import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return createDtoFromEntity(list);
	}
	
	private List<UserDTO> createDtoFromEntity(List<User> entities){
		List<UserDTO> dtos = new ArrayList<>();
		for(int x = 0; x<entities.size(); x++) {
			dtos.add(new UserDTO(entities.get(x)));
		}
		return dtos;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public UserDTO findByEmail(String email) {
		User obj = repository.findByEmail(email);
		return new UserDTO(obj);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		} 
		logger.info("User found: " + username);
		return user;
	}
	
}
