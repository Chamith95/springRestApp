package com.chamith.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.chamith.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
}
