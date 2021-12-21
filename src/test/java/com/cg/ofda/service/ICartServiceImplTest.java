package com.cg.ofda.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.ItemIdNotFoundException;
import com.cg.ofda.dao.ICartItemRepositoryDao;
import com.cg.ofda.dao.ICartRepositoryDao;
import com.cg.ofda.dao.ICustomerRepository;
import com.cg.ofda.dao.IFoodRepositoryDao;
import com.cg.ofda.model.Cart;
import com.cg.ofda.model.CartItem;
import com.cg.ofda.model.Customer;
import com.cg.ofda.model.Food;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ICartServiceImplTest {
	@InjectMocks
	ICartServiceImpl service;
	@Mock
	ICartRepositoryDao dao;
	@Mock
	ICustomerRepository dao1;
	@Mock
	ICartItemRepositoryDao itemdao;
	@Mock
	IFoodRepositoryDao dao3;

	@Test
	public void createCart() {
		List<CartItem> cartItem = new ArrayList<CartItem>();
		Cart cart = new Cart();
		cart.setCartId(1);
		Customer customer1 = new Customer();
		customer1.setCustomerId(1);
		cart.setCustomer(customer1);
		cart.setCartItem(cartItem);
		Food p = new Food();
		p.setFoodCost(400.0);
		CartItem item = new CartItem();
		item.setQuantity(4);
		cartItem.add(item);
		when(dao.findById(1)).thenReturn(Optional.of(cart));
		Cart cart1 = new Cart();
		cart1.setCustomer(null);
		cart.setCustomer(customer1);
		cart.setTotal(555);
		when(itemdao.findByCustomer(customer1)).thenReturn(cartItem);
		when(dao.save(cart)).thenReturn(cart);

	}

	@Test
	public void clearCart() {
		Customer cust = new Customer();
		cust.setCustomerId(1);
		when(dao1.existsById(1)).thenReturn(true);
		List<CartItem> c = new ArrayList<CartItem>();
		CartItem cart = new CartItem();
		cart.setCustomer(cust);
		c.add(cart);
		when(itemdao.findByCustomer(cust)).thenReturn(c);

	}

	@Test
	public void deleteCartItem() throws CustomerIdNotFoundException, ItemIdNotFoundException {
		Customer cust = new Customer();
		cust.setCustomerId(1);
		when(dao1.existsById(1)).thenReturn(true);
		CartItem item = new CartItem();
		item.setItemId(1);
		item.setCustomer(cust);
		when(itemdao.existsById(1)).thenReturn(true);
		assertEquals(service.deleteCartItem(1, 1), "deleted");

	}
	
	@Test
	public void updateCartItem()
	{
		Customer cust = new Customer();
		cust.setCustomerId(1);
		Cart cart= new Cart();
		cart.setCustomer(cust);
		when(dao.existsById(1)).thenReturn(true);
		CartItem item = new CartItem();
		item.setQuantity(3);
		assertNotNull(dao.existsById(1));
		
		
	}

	@Test
	public void addFoodTocart()
	{
		Food p = new Food();
		p.setFoodId(1);
		when(dao3.existsById(1)).thenReturn(true);
		CartItem cart = new CartItem();
		cart.setFood(p);
		when(itemdao.save(cart)).thenReturn(cart);
	}
}
