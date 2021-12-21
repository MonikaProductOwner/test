package com.cg.ofda.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.dao.loginRepository;
import com.cg.ofda.model.Login;

@Service
@Transactional

public class ILogin {
	
	@Autowired
	loginRepository LoginRepo;
	
	public Login addNewUser(Login user) {
		
		LoginRepo.save(user);
		return user;
	}
	
	
public Login signIn(String userName,String password)  {
	if(!LoginRepo.existsById(userName))
		return null;
	Login user1=LoginRepo.findById(userName).orElse(null);
	if(!user1.getPassword().equals(password))
		return null;
	return user1;
}
}


