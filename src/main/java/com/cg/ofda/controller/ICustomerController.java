package com.cg.ofda.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.model.Customer;
import com.cg.ofda.service.ICustomerServiceImpl;


/*
 * Author : Monika S A
 * Date : 1-12-2021
 * Description : This is Customer Controller
 */

@RestController    //it will handle both Http Protocal methods and Controller
@RequestMapping("/customer") //mapping the request
@CrossOrigin(origins = "http://localhost:4200") //CrossOrigin for changing postman to angular
public class ICustomerController {
	
	@Autowired
	private ICustomerServiceImpl customerService;
	static final Logger LOGGER = LoggerFactory.getLogger(ICustomerController.class);
	
	/***********************************************************************************************************************
	 * Method: addCutomer 
	 * Description: It is used to add customer into customer table
	 * @param customer: customer's reference variable.
	 * @returns Customer It returns Customer with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ************************************************************************************************************************/
	
	@PostMapping("/insert")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		LOGGER.info("Insert Customer");
		Customer cust=customerService.addCustomer(customer);
		if(cust==null){
		return new ResponseEntity("Customer Not Found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <Customer>(cust ,HttpStatus.OK);
	}
	
	/**************************************************************************************************************************
	 * Method: viewCutomers 
	 * Description: It is used to add customer into customer table
	 * @returns List<Customer> It returns List of Customers with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 *****************************************************************************************************************************/

	@GetMapping("/retrieve")
	public ResponseEntity<List<Customer>> viewCustomers(){
		LOGGER.info("Retrieve Customer");
		System.out.println("List of all Customers ");
		List<Customer> cust=customerService.viewCustomers();
		if(cust.isEmpty()) {
			return new ResponseEntity("Customer Not Found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(cust, HttpStatus.OK);
	}
	
	/****************************************************************************************************************
	 * Method: editCutomer 
	 * Description: It is used to update customer info into customer table
	 * @param customer: customer's reference variable.
	 * @returns Customer It returns Customer with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ************************************************************************************/
	
	
	@PutMapping("/edit")
	public ResponseEntity<Object> editCustomer(@RequestBody Customer customer) throws CustomerIdNotFoundException{
		LOGGER.info("Edit Customer Details");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Customer p=customerService.updateCustomer(customer);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
}
	
	/******************************************************************************************************
	 * Method: deleteCutomer 
	 * Description: It is used to delete one customer details from customer table
	 * @param customer: int customerId
	 * @returns String It returns a message that the customer information is deleted.
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 *******************************************************************************************************/
	
	@DeleteMapping("/DeleteCustomer/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int customerId) throws CustomerIdNotFoundException {
		LOGGER.info("Delete Customer by id");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p=customerService.deleteCustomer(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
}
	
	/*******************************************************************************************************
	 * Method: viewCutomer 
	 * Description: It is used to view one customer details from customer table
	 * @param customer: int customerId
	 * @returns Optional<Customer> It returns Customer with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 **********************************************************************************************************/
	
	@GetMapping("/viewonecustomer/{id}")
	public ResponseEntity<Object> viewCutomer(@PathVariable("id") int customerId) throws CustomerIdNotFoundException{
		LOGGER.info("View one Customer by id");
		System.out.println("Customer info ");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Optional<Customer> p=customerService.viewCustomer(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
}
}
