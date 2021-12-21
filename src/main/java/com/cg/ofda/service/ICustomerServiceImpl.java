package com.cg.ofda.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.dao.ICustomerRepository;
import com.cg.ofda.model.Customer;


@Service
public class ICustomerServiceImpl implements ICustomerService {
	
	@Autowired
	ICustomerRepository customerRepo;


	@Override
	public Customer addCustomer(Customer customer) {
				
			Customer cust=customerRepo.save(customer);
			
			return cust;
	}


	@Override
	public Customer updateCustomer(Customer customer)throws CustomerIdNotFoundException{
		boolean b=customerRepo.existsById(customer.getCustomerId());
		if(!b) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		}
		else {
			
			
			return customerRepo.save(customer);
		}
	}

	@Override
	public String deleteCustomer(int customerId) throws CustomerIdNotFoundException {
		boolean b=customerRepo.existsById(customerId);
		if(!b) {
			return "No Customer found for this id";
		}
		else {
			customerRepo.deleteById(customerId);
			
			return "Deleted Customer Successfully!";
		}
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> customer=customerRepo.findAll();
		return customer;
	}

	@Override
	public Optional<Customer> viewCustomer(int customerId) throws CustomerIdNotFoundException {
		
		return customerRepo.findById(customerId);
	}

}
