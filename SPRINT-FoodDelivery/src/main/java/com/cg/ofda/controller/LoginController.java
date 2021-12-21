package com.cg.ofda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ofda.model.Login;
import com.cg.ofda.service.ILogin;

@RestController
@RequestMapping("/Admin_Login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ILogin iLog;

	
	@PostMapping("/Register")
	public Login register(@RequestBody Login login) {
	
		return iLog.addNewUser(login);
	}
	
	
	@PostMapping("/Login/{login}/{password}")
    public Login login(@PathVariable("login") String userName,@PathVariable("password") String password)  {
        System.out.println(userName+","+password);
        return iLog.signIn(userName, password);
    }
	


@GetMapping("/Logout")
	public ResponseEntity<String> logout() {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	}
	

