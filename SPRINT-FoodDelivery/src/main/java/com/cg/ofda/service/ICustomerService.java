package com.cg.ofda.service;

import java.util.List;
import java.util.Optional;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.model.Customer;


public interface ICustomerService {
	Customer addCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer) throws CustomerIdNotFoundException;

	String deleteCustomer(int customerId) throws CustomerIdNotFoundException;

	List<Customer> viewCustomers();

	Optional<Customer> viewCustomer(int customerId) throws CustomerIdNotFoundException;
}

	