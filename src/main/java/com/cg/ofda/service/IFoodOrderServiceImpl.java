package com.cg.ofda.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.Exceptions.CartIdNotFoundException;
import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.OrderIdNotFoundException;
import com.cg.ofda.dao.ICartRepositoryDao;
import com.cg.ofda.dao.ICustomerRepository;
import com.cg.ofda.dao.IFoodOrderRepository;
import com.cg.ofda.model.Cart;
import com.cg.ofda.model.Customer;
import com.cg.ofda.model.Order;


@Service
@Transactional

public class IFoodOrderServiceImpl implements IFoodOrderService{
	
	@Autowired
		IFoodOrderRepository repo;

		@Autowired
		ICustomerRepository custdao;

		@Autowired
		ICartRepositoryDao cartrepo;
		

	public List<Order> viewOrder(int customerId) throws CustomerIdNotFoundException {
			Optional<Customer> byId = custdao.findById(customerId);
			
			if (!byId.isPresent()) {
				throw new CustomerIdNotFoundException("Wrong Customer Id");
			} else {
					List<Order> ord = repo.findByCustomer(byId);
					double finalprice=0.0;
					for(int i=0;i<ord.size();i++) {
					double finalPrice = (ord.get(i).getCart().getTotal());//- (ord.get(i));
					System.out.println(finalPrice);
					ord.get(i).setFinalPrice(finalPrice);
					repo.save(ord.get(i));
					}
					return repo. findByCustomer(byId);
				
			}
		}

		
		
		// Returns a list of all the orders
		@Override
		public List<Order> listAllOrder() {
			System.out.println("List all orders in service layers");
			List<Order> list = repo.findAll();
			return list;
		}

		// Customer can Update His Order
		@Override
		public Order updateOrder(Order ord) throws OrderIdNotFoundException {
			boolean b = repo.existsById(ord.getOrderId());
			if (!b) {
				throw new OrderIdNotFoundException();
			} else
				System.out.println("Update Order Successfully");
			return repo.save(ord);
		}

			
		// Customer can Delete The Particular Order
		public String cancelOrder(int orderid) throws OrderIdNotFoundException {
			if (!repo.existsById(orderid))
				throw new OrderIdNotFoundException();
			else
				repo.deleteById(orderid);

			return "Order Deleted Successfully";
		}

			
		
		public Order bookOrderInfo(int cartId, int customerId) throws CartIdNotFoundException,CustomerIdNotFoundException{
			Optional<Customer> byId = custdao.findById(customerId);

			Optional<Cart> cartIde = cartrepo.findById(cartId);
			
			Order ord = new Order();
			if (!cartIde.isPresent()) {
	     		throw new CartIdNotFoundException("Cart not found");
			} 
			else if(!byId.isPresent()) {
				throw new CustomerIdNotFoundException("Customer Id Not Found");
			}
			
			else {
				ord.setOrderStatus("Booked");
				ord.setCustomer(byId.get());
				ord.setCart(cartIde.get());
				repo.save(ord);
				return ord;
			}

		}

	}



