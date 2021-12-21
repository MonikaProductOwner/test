package com.cg.ofda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.ofda.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
	

}