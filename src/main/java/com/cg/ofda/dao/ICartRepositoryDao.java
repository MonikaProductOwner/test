package com.cg.ofda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ofda.model.Cart;
import com.cg.ofda.model.Customer;

@Repository
public interface ICartRepositoryDao extends JpaRepository<Cart,Integer>  {

	public Cart findByCustomer(Customer customer);
	
}
