package com.cg.ofda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cg.ofda.Exceptions.CustomerIdNotFoundException;
import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.model.Cart;
import com.cg.ofda.model.CartItem;
import com.cg.ofda.service.ICartServiceImpl;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import com.cg.ofda.Exceptions.ItemIdNotFoundException;

/*
 * Author : Payal Tanaji Pawale
 * Date : 1-12-2021
 * Description : This is Cart Controller
*/


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class ICartController {
	
	
static final Logger LOGGER = LoggerFactory.getLogger(ICartController.class);
//Logger is used for all internal logging
	
	@Autowired
	ICartServiceImpl serviceobj;

	/************************************************************************************
	 * Method: createCart
	 * Description: It is used to create a cart into cart table
	 * @param cartItem: int customerId
	 * @returns cart: It returns cart with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Payal Tanaji Pawale
     * Created Date - 1-12-2021 
	 * 
	 ************************************************************************************/
	
	
	@PostMapping("/createCart/{id}")
	public ResponseEntity<Object> createCart(@PathVariable("id") int customerId) throws CustomerIdNotFoundException{
		LOGGER.info("create-cart URL is opened");
		LOGGER.info("createCart() is initiated");
		//
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		Cart p= serviceobj.createCart(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}

	/************************************************************************************
	 * Method: addFoodToCart
	 * Description: It is used to add cartItem into cartItem table
	 * @param cartItem: cartItems reference variable and int id
	 * @returns cartItem: It returns cartItem with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Payal Tanaji Pawale
     * Created Date -  1-12-2021 
	 * 
	 ************************************************************************************/
	@PostMapping("/addItem")
	public ResponseEntity<Object> addFoodToCart(@RequestBody Map<String, Object> requestData) throws CustomerIdNotFoundException, FoodIdNotFoundException {
		LOGGER.info("add-item URL is opened");
		LOGGER.info("addFoodToCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		CartItem p = serviceobj.addFoodToCart(requestData);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException | FoodIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}
	
	
	/************************************************************************************
	 * Method: deleteCartItem
	 * Description: It is used to delete a cartItem fromm cartItem table
	 * @param cartItem: int customerId,int itemId.
	 * @returns String: It returns a String message
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Payal Tanaji Pawale
     * Created Date - 2-12-2021
	 * 
	 ************************************************************************************/

	@DeleteMapping("/deleteItemFromCart/{cutomerid}/{itemid}")
	public ResponseEntity<Object> deleteCartItem(@PathVariable("cutomerid") int customerId,@PathVariable("itemid") int itemId) throws CustomerIdNotFoundException, ItemIdNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.deleteCartItem(customerId,itemId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}
		catch(CustomerIdNotFoundException | ItemIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}
	
	/************************************************************************************
	 * Method: updateCartQuantity
	 * Description: It is used to update a cartItem using quantity into cartItem table
	 * @param cartItem: int customerId,int itemId,int quant.
	 * @returns cartItem: It returns cartItem with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Payal Tanaji Pawale
     * Created Date -  1-12-2021 
	 * 
	 ************************************************************************************/
	
	
	@PutMapping("/updateCartQuantity/{customerid}/{itemid}/{quant}")
	public ResponseEntity<Object> updateCartQuantity(@PathVariable("customerid") int customerId,@PathVariable("itemid") int itemId,@PathVariable("quant") int quant) throws CustomerIdNotFoundException, ItemIdNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		CartItem p= serviceobj.updateCartQuantity(customerId,itemId,quant);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException | ItemIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}		
	}
	
		
	
	
	@GetMapping("/viewAllCartitems/{id}")
	public ResponseEntity<Object> viewAllCartitem(@PathVariable("id") int custid) throws CustomerIdNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		List<CartItem> p = serviceobj.viewitemList(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping("/viewAllCartitems")
	public List<CartItem> viewAllCartitems(){
		LOGGER.info("View All Cartitems");
		return serviceobj.viewallitemList();
	}
	
	
	
	@GetMapping("/viewCart/{id}")
	public ResponseEntity<Object> viewCart(@PathVariable("id") int custid)  throws CustomerIdNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			java.util.Optional<Cart> p = serviceobj.viewCart(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/viewCart")
	public List<Cart> viewAllCarts() {
		LOGGER.info("View All Cartitems");
		return serviceobj.viewCarts();
	}
	
	
	/************************************************************************************
	 * Method: clearCart
	 * Description: It is used to delete a cart from cart table
	 * @param cartItem: int customerId.
	 * @returns String: It returns a String message
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Payal Tanaji Pawale
     * Created Date -  2-12-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping("/clearCart/{id}")
	public ResponseEntity<Object> clearCart(@PathVariable("id") int customerId) throws CustomerIdNotFoundException{
		LOGGER.info("clear-cart URL is opened");
		LOGGER.info("clearCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.clearCart(customerId);
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



  