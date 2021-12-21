package com.cg.ofda.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ofda.dao.loginRepository;
import com.cg.ofda.model.Login;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginTest {
	
	@InjectMocks
	ILogin service;
	@Mock
	loginRepository dao;
	@Test
	public void addUser()
	{
		Login login = new Login();
		login.setUserid("1");
		when(service.addNewUser(login)).thenReturn(login);
	}
	
	@Test
	public void loginn()
	{
		Login login = new Login();
		login.setUserid("1");
		login.setUserName("zan");
		login.setPassword("de");
		when(dao.existsById("1")).thenReturn(true);
		Login user1 = new Login();
		user1.setUserid("1");
		user1.setUserName("zan");
		user1.setPassword("de");	
		assertEquals(login.getUserid(), "1");
		assertNotEquals(service.signIn("zan", "de"), "Logged In SuccessFully");
		
		
	}

}
