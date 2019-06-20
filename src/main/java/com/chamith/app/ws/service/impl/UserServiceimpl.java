package com.chamith.app.ws.service.impl;

import java.util.ArrayList;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chamith.app.ws.exceptions.UserServiceException;
import com.chamith.app.ws.io.entity.UserEntity;
import com.chamith.app.ws.io.repository.UserRepository;
import com.chamith.app.ws.service.UserService;
import com.chamith.app.ws.shared.Utils;
import com.chamith.app.ws.shared.dto.UserDto;
import com.chamith.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		
		
		if(userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exists");
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserId=utils.generateUserId(30);
		
		userEntity.setEncrtptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		
		UserEntity storedUserDetails=userRepository.save(userEntity);
		
		UserDto returnvalue=new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnvalue);
		
		return returnvalue;
	}
	
	@Override
	public UserDto getUser(String email)
	{
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity== null) {
			throw new UsernameNotFoundException(email);
		}
		UserDto returnvalue=new UserDto();
		BeanUtils.copyProperties(userEntity, returnvalue);
		return returnvalue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=userRepository.findByEmail(email);
		
		if(userEntity== null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new User(userEntity.getEmail(),userEntity.getEncrtptedPassword(),new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue =new UserDto();
		UserEntity userEntity =userRepository.findByUserId(userId);
		if(userEntity== null) {
		throw new UsernameNotFoundException(userId);
	}
	
	BeanUtils.copyProperties(userEntity, returnValue );
	return returnValue;
	}

	@Override
	public UserDto updateUser(String userid, UserDto user) {
		UserDto returnValue =new UserDto();
		UserEntity userEntity =userRepository.findByUserId(userid);
		if(userEntity== null) {
		throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
	}
		
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		
		UserEntity updatedUserDetails=userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);
		
		return returnValue;
	}




}
