package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.Exceptions.CartIdNotFoundException;
import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.OrderIdNotFoundException;
import com.cg.ofda.model.Order;

public interface IFoodOrderService {
	
Order bookOrderInfo(int cartId,int customerId)throws CartIdNotFoundException,CustomerIdNotFoundException;
	
	Order updateOrder(Order ord); //done

	String cancelOrder(int orderId) throws OrderIdNotFoundException; //done

    Iterable<Order> listAllOrder();//done

    List<Order> viewOrder(int customerId) throws CustomerIdNotFoundException; //done

	
    
    
    
}


