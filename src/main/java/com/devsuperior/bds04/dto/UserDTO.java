package com.devsuperior.bds04.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.bds04.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private List<RoleDTO> roles = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String email, List<RoleDTO> roles) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		email = entity.getEmail();
		roles.forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}



	
	
	
	
}
