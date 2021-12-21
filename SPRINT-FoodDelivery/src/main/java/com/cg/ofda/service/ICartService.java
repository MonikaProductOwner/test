package com.cg.ofda.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.Exceptions.ItemIdNotFoundException;
import com.cg.ofda.model.Cart;
import com.cg.ofda.model.CartItem;

public interface ICartService {

	public Cart createCart(int customerId)throws CustomerIdNotFoundException;
	public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerIdNotFoundException,ItemIdNotFoundException;
	public String deleteCartItem(int customerId, int itemId)throws CustomerIdNotFoundException,ItemIdNotFoundException;
	public String clearCart(int customerId)throws CustomerIdNotFoundException;
	public CartItem addFoodToCart(Map<String, Object> requestData)throws CustomerIdNotFoundException,FoodIdNotFoundException;
//	public CartItem addFoodToCart(CartItem cartitem)throws CustomerIdNotFoundException,FoodIdNotFoundException;
	public List<CartItem> viewitemList(int custid)throws CustomerIdNotFoundException;
	public List<CartItem> viewallitemList();
	public List<Cart> viewCarts();
	public Optional<Cart> viewCart(int custid)throws CustomerIdNotFoundException;
}


