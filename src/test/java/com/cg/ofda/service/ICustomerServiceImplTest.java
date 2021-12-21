package com.cg.ofda.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.dao.ICustomerRepository;
import com.cg.ofda.model.Customer;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ICustomerServiceImplTest {
	@InjectMocks
	ICustomerServiceImpl service;
	@Mock
	ICustomerRepository dao;
	
	
	/***************************************************************************************************************************
	 * Method: addCustomerTest 
	 * Description: It is used to add one customer details from customer table and test the return
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ***************************************************************************************************************************/
	
	
	@Test
	public void addCustomerTest() {
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("bani");
		c.setCustomerMobile((long) 999999);
		c.setCustomerAddress("sdfgh");
		c.setCustomerEmail("sdfgrhthn");
		c.setUserName("adfe");
		c.setPassword("dfgvdfgb");
		when(service.addCustomer(c)).thenReturn(c);

	}
	
	/***************************************************************************************************************************
	 * Method: deleteCustomer 
	 * Description: It is used to test delete method
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ***************************************************************************************************************************/


	@Test
	public void deleteCustomer() throws CustomerIdNotFoundException {

		Customer cust = new Customer();
		cust.setCustomerId(2);
		Mockito.when(dao.existsById(cust.getCustomerId())).thenReturn(true);
		service.deleteCustomer(cust.getCustomerId());
		verify(dao, Mockito.atLeastOnce()).deleteById(2);
	}
	
	/***************************************************************************************************************************
	 * Method: viewCustomers
	 * Description: It is used to test view all customer method
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ***************************************************************************************************************************/
	

	@Test
	public void viewCustomer() {
		List<Customer> customer = new ArrayList<Customer>();
		Customer cust = new Customer();
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("bani");
		c.setCustomerMobile((long) 999999);
		c.setCustomerAddress("kharag");
		c.setCustomerEmail("abc@gmail");
		c.setUserName("bani");
		c.setPassword("123");
		customer.add(c);
		cust.setCustomerName("bidya");
		cust.setCustomerId(2);
		cust.setCustomerAddress("inda");
		cust.setCustomerEmail("xyz@gmail.com");
		cust.setPassword("bidya");
		cust.setUserName("456");
		cust.setCustomerMobile((long) 77767788);
		customer.add(cust);
		when(service.viewCustomers()).thenReturn(customer);
		assertEquals(2, service.viewCustomers().size());
	}
	
	/***************************************************************************************************************************
	 * Method: viewCustomers
	 * Description: It is used to test view one customer method
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ***************************************************************************************************************************/


	@Test
	public void viewCostomerById() throws CustomerIdNotFoundException {

		List<Customer> cust = new ArrayList<Customer>();
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("bani");
		c.setCustomerMobile((long) 999999);
		c.setCustomerAddress("kharag");
		c.setCustomerEmail("abc@gmail");
		c.setUserName("bani");
		c.setPassword("123");
		cust.add(c);
		Mockito.when(dao.existsById(1)).thenReturn(true);
		Mockito.when(dao.findById(1)).thenReturn(Optional.of(c));
		assertEquals(c.getCustomerId(), service.viewCustomer(1).get().getCustomerId());
	}
	
	/***************************************************************************************************************************
	 * Method: updateCustomer 
	 * Description: It is used to test update method 
	 * Created By-Monika S A
     * Created Date -  1-12-2021 
	 * 
	 ***************************************************************************************************************************/


	@Test
	void updateCustomer() throws CustomerIdNotFoundException {
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("bani");
		c.setCustomerMobile((long) 999999);
		c.setCustomerAddress("kharag");
		c.setCustomerEmail("abc@gmail");
		c.setUserName("bani");
		c.setPassword("123");
		when(dao.existsById(c.getCustomerId())).thenReturn(true);
		c.setCustomerId(1);
		c.setCustomerName("banik");
		c.setCustomerMobile((long) 999999);
		c.setCustomerAddress("kharag");
		c.setCustomerEmail("abc@gmail");
		c.setUserName("bani");
		c.setPassword("123");
		when(service.updateCustomer(c)).thenReturn(c);
		assertEquals("banik", c.getCustomerName());

	}
}
