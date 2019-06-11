package com.chamith.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chamith.app.ws.UserRepository;
import com.chamith.app.ws.io.entity.UserEntity;
import com.chamith.app.ws.service.UserService;
import com.chamith.app.ws.shared.Utils;
import com.chamith.app.ws.shared.dto.UserDto;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		
		
		if(userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exists");
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserId=utils.generateUserId(30);
		
		userEntity.setEncrtptedPassword("testPassword");
		userEntity.setUserId(publicUserId);
		
		UserEntity storedUserDetails=userRepository.save(userEntity);
		
		UserDto returnvalue=new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnvalue);
		
		return returnvalue;
	}

}
