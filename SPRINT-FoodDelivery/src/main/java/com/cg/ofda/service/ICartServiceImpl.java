package com.cg.ofda.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.Exceptions.ItemIdNotFoundException;
import com.cg.ofda.dao.ICartItemRepositoryDao;
import com.cg.ofda.dao.ICartRepositoryDao;
import com.cg.ofda.dao.ICustomerRepository;
import com.cg.ofda.dao.IFoodRepositoryDao;
import com.cg.ofda.model.Cart;
import com.cg.ofda.model.CartItem;
import com.cg.ofda.model.Customer;
import com.cg.ofda.model.Food;

@Service

public class ICartServiceImpl implements ICartService {
	
	@Autowired
	ICartRepositoryDao cartdao;
	@Autowired
	ICartItemRepositoryDao itemdao;
	@Autowired
	IFoodRepositoryDao fooddao;
	@Autowired
	ICustomerRepository customerdao;
	
	
	//customer can create a cart by passing customerID
		@Override
		public Cart createCart(int customerId) throws CustomerIdNotFoundException{
			Cart cart=new Cart();
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			} else {
				Customer customer=customerdao.findById(customerId).get();
				cart.setCustomer(customer);
				
				List<CartItem> cartItem = itemdao.findByCustomer(customer);
				double total=0.0;
				for(int i=0; i<cartItem.size(); i++)
				{
					total=total+(cartItem.get(i).getFood().getFoodCost())*(cartItem.get(i).getQuantity());
				}
				 cart.setTotal(total);
				cart.setCartItem(cartItem);
				return cartdao.save(cart);
			}
			
			
		}
		@Override
		public List<CartItem> viewitemList(int custid)throws CustomerIdNotFoundException {
			if (!customerdao.existsById(custid)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			}else {
				Customer customer=customerdao.findById(custid).get();
				List<CartItem> cartItem = itemdao.findByCustomer(customer);
				System.out.println("Getting data from db" + cartItem);
				return cartItem;
			}
		}
		@Override
		public List<CartItem> viewallitemList(){
			List<CartItem> cartitem=itemdao.findAll();
			return cartitem;
		}
		@Override
		public Optional<Cart> viewCart(int custid)throws CustomerIdNotFoundException {
			if (!customerdao.existsById(custid)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			}else {
				Customer customer=customerdao.findById(custid).get();
				Optional<Cart> cart = Optional.ofNullable(cartdao.findByCustomer(customer));
				System.out.println("Getting data from db" + cart);
				return cart;
			}
		}
		@Override
		public List<Cart> viewCarts(){
			List<Cart> cart=cartdao.findAll();
			return cart;
		}
		//customer can clear the cart created by passing customerId
		@Override
		public String clearCart(int customerId)throws CustomerIdNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			}else {
				Optional<Customer> byId = customerdao.findById(customerId);
				int cartId=cartdao.findByCustomer(byId.get()).getCartId();
				cartdao.deleteById(cartId);
				return "Cart Deleted";
			}
		}


		//customer can delete cartItem by sending customerId and itemId
		@Override
		public String deleteCartItem(int customerId, int itemId)throws CustomerIdNotFoundException,ItemIdNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			} else {
				boolean b = itemdao.existsById(itemId);
				if (!b)
					throw new  ItemIdNotFoundException("No CartItem found for this id");
				else {
					itemdao.deleteById(itemId);
					return "deleted";
				}
			}
		}

		//customer can update cart quantity by passing customerId, itemId and quantity.
		@Override
		public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerIdNotFoundException,ItemIdNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
				
			} else {
				Optional<CartItem> byId2 = itemdao.findById(itemId);
				if (!byId2.isPresent())
					throw new  ItemIdNotFoundException("No CartItem found for this id");
					
				else {
					CartItem cartItem = byId2.get();
					cartItem.setQuantity(quantity);
					return itemdao.save(cartItem);
				}

			}
		}

		//customer can add Food to cartItem by passing customerId and body 
		@Override
		public CartItem addFoodToCart( Map<String, Object> requestData)throws CustomerIdNotFoundException,FoodIdNotFoundException {
			CartItem cartItem = new CartItem();
			int customerId=(int) requestData.get("customerId");
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerIdNotFoundException("No Customer found for this id");
			} else {
				int foodId = (int) requestData.get("foodId");
				if(!fooddao.existsById(foodId)) {
					throw new  FoodIdNotFoundException("No Food found for this id");
				}

				else {
					Customer customer=customerdao.findById(customerId).get();
					Food food=fooddao.findById(foodId).get();
					int quantity=(int) requestData.get("quantity");
					String foodSize=(String) requestData.get("foodSize");
					cartItem.setCustomer(customer);
					cartItem.setFood(food);
					//cartItem.setFoodItem(fooditem);
					cartItem.setQuantity(quantity);
					System.out.println(cartItem.toString());
					System.out.println(customer.toString());
					System.out.println(food.toString());
					return itemdao.save(cartItem);
				}
				
			}
		}
}

		
		