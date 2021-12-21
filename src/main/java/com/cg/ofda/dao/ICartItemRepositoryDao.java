package com.cg.ofda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ofda.model.CartItem;
import com.cg.ofda.model.Customer;

@Repository
public interface ICartItemRepositoryDao extends JpaRepository<CartItem, Integer>{
	@Autowired
	public List<CartItem> findByCustomer(Customer customer);

}



