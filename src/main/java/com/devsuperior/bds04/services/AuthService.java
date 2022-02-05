package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		return userRepository.findByEmail(username);
	}
	
	public void validateVisitorOrMember(Long userId) throws Exception {
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.hasRole("ROLE_MEMBER")) {
			throw new Exception("not member");
		}
	}
	
}
