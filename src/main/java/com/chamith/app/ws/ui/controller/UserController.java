package com.chamith.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chamith.app.ws.exceptions.UserServiceException;
import com.chamith.app.ws.service.UserService;
import com.chamith.app.ws.shared.dto.UserDto;
import com.chamith.app.ws.ui.model.request.UserDetailsRequestModel;
import com.chamith.app.ws.ui.model.response.ErrorMessages;
import com.chamith.app.ws.ui.model.response.OperationStatusModel;
import com.chamith.app.ws.ui.model.response.RequestOperationName;
import com.chamith.app.ws.ui.model.response.RequestOperationStatus;
import com.chamith.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id)
	{
		UserRest returnValue=new UserRest();
		
		UserDto userDto =userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest CreateUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserRest returnValue=new UserRest();
		
		if(userDetails.getFirstname().isEmpty()) throw new NullPointerException("The object is null");
		
//		UserDto userDto =new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto=modelMapper.map(userDetails,UserDto.class);
		
		UserDto createdUser=userService.createUser(userDto);
		returnValue=modelMapper.map(createdUser,UserRest.class);

		
		return returnValue;
		
	}
	
	
	@PutMapping(path="/{id}",
			consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String id,@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue=new UserRest();
		

		
		UserDto userDto =new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updateUser=userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel DeleteUser(@PathVariable String id)
	{
		OperationStatusModel returnvalue=new OperationStatusModel();
		returnvalue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		
		returnvalue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnvalue;
	}
	
	@GetMapping(produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value="page" , defaultValue="0")int page,
			@RequestParam(value="limit" ,defaultValue="25")int limit){
		 List<UserRest> returnValue =new ArrayList<>();
		 
		 List<UserDto> users= userService.getUsers(page,limit);
		 
		 for (UserDto userDto :users) {
			 UserRest userModel =new UserRest();
			 BeanUtils.copyProperties(userDto, userModel);
			 returnValue.add(userModel);
		 }
		 
		 return returnValue;
	}
	

}
