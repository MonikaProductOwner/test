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

import com.cg.ofda.Exceptions.CartIdNotFoundException;
import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.OrderIdNotFoundException;
import com.cg.ofda.model.Order;
import com.cg.ofda.service.IFoodOrderServiceImpl;


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")

public class IFoodOrderController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(IFoodOrderController.class);
	
	@Autowired 
	IFoodOrderServiceImpl service;


@GetMapping("/viewAllOrder")  
	public List<Order> viewAllOrder(){
	  LOGGER.info("View All Orders");
	  List<Order> orderList= service.listAllOrder();
	  return orderList;
	}	


@PostMapping("/insertOrder/{cartid}/{customerId}")
	public ResponseEntity<Object> bookOrderInfo(@PathVariable("cartid")int cartId,@PathVariable("customerId") int customerId) throws CartIdNotFoundException,CustomerIdNotFoundException
	{
	  LOGGER.info("bookOrder-info URL is opened");
	  LOGGER.info("bookOrder() is initiated");
	  Map<String,Object> res=new HashMap<String,Object>();
	  try {
		  Order p=service.bookOrderInfo(cartId, customerId);
		  res.put("status", HttpStatus.OK.value());
		  res.put("data", p);
		  return new ResponseEntity<>(res,HttpStatus.OK);
	  }catch(CartIdNotFoundException | CustomerIdNotFoundException e)
	  {
	  res.put("status",HttpStatus.NOT_FOUND.value());
	  res.put("data",e.getMessage());
	  return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	  }
	}



@DeleteMapping("/DeleteOrder/{orderid}")
	public ResponseEntity<String> cancelOrder(@PathVariable("orderid")int id)throws OrderIdNotFoundException
    {
		
		String p= service.cancelOrder(id);
		if (p == null) {
			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }



@PutMapping("/UpdateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order)
    {
      Order ord=service.updateOrder(order);
      if(ord==null){
      return new ResponseEntity("Order Not Found",HttpStatus.NOT_FOUND);
 	}
 	   return new ResponseEntity<Order>(ord ,HttpStatus.OK);
 	}


 @GetMapping("/viewOrderByOrderandCustomerId/{customerId}")
     public ResponseEntity<Object> viewOrder(@PathVariable("customerId") int customerId) throws CustomerIdNotFoundException
    {
      LOGGER.info("View Your Orders");
      Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<Order> p=service.viewOrder(customerId);
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




