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

/*
 * Author : Mundhe Amol
 * Date : 1/12/2021
 * Description : This is Admin_Login_Controller
*/

@RestController
@RequestMapping("/Admin_Login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ILogin iLog;
	
	/*
	 * Method : register
	 * Description : adding to the database
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created by : Mundhe Amol
	 * Date of Creation : 1/12/2021
	 */
	
	@PostMapping("/Register")
	public Login register(@RequestBody Login login) {
	
		return iLog.addNewUser(login);
	}
	
	/*
	 * Method : login
	 * Description : checking username and password is same or not
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestParam : It is used to read the HTML form data provided by a user and bind it to the request parameter.
	 * Created by : Mundhe Amol
	 * Date of Creation : 1/12/2021
	 */
	
	@PostMapping("/Login/{login}/{password}")
    public Login login(@PathVariable("login") String userName,@PathVariable("password") String password)  {
        System.out.println(userName+","+password);
        return iLog.signIn(userName, password);
    }
	
	/*
	 * Method : logout
	 * Description : logging out session
	 * @GetMapping : It is used to handle GET type of request method.
	 * ResponseEntity : It represents the whole HTTP response- status code, headers, and body.
	 * Created by : Mundhe Amol
	 * Date of Creation : 1/12/2021
	 */

@GetMapping("/Logout")
	public ResponseEntity<String> logout() {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	}
	

