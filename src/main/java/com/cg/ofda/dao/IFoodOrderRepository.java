package com.cg.ofda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ofda.model.Cart;
import com.cg.ofda.model.Customer;
import com.cg.ofda.model.Order;


@Repository

public interface IFoodOrderRepository extends JpaRepository<Order,Integer> {

	public List<Order>  findByCustomer(Optional<Customer> byId);
	
	public List<Cart>  findByCart(Cart cart);
	
	public String deleteById(int orderId);
}