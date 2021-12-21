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


import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.Exceptions.InvalidMinCostException;
import com.cg.ofda.model.Food;
import com.cg.ofda.service.IFoodServiceImpl;



@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "http://localhost:4200")

public class IFoodController {
	
	@Autowired
	private IFoodServiceImpl serviceobj;
	static final Logger LOGGER = LoggerFactory.getLogger(IFoodController.class);

	/*
	 * Method: addFood
	 * Description: It is used to add food into food table
	 * @returns Food It returns Food with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By : Siddharth Katule
     * Created Date - 02-12-2021
	 */ 
@PostMapping("/add")
	public ResponseEntity<Food> addFood(@RequestBody Food food) {
		LOGGER.info("Add Food");
		Food p = serviceobj.addFood(food);
		if (p == null) {
			return new ResponseEntity("Food Not Found!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Food>(p, HttpStatus.OK);
	}

	
/*
 * Method: deleteFood 
 * Description: It is used to delete food into food table
 * @param food: int id
 * @returns String It returns a message that the food information is deleted.
 * @DeleteMapping: It is used to handle the HTTP POST requests matched with given URI expression.
 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
 * Created By-Siddharth Katule
 * Created Date - 02-12-2021
 
*/
@DeleteMapping("/delete/{id}")
	public String deleteFood(@PathVariable("id") int id) throws FoodIdNotFoundException {
		LOGGER.info("Delete Food");
		return serviceobj.deleteFood(id); 
	} 

/*
 * Method: viewAllFood 
 * Description: It is used to view all food details from food table
 * @returns List<Food > It returns List of Food with details.
 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
 * Created By-Siddharth Katule
 * Created Date -  02-12-2021 
 * 
*/
@GetMapping("/viewAllFood")
	public List<Food> viewAllFood() {
		LOGGER.info("View All Food");
		return serviceobj.viewFoodList();
	}

/*
 * Method: getFood
 * Description: It is used to view one food details from food table
 * @param food: int id
 * @returns Optional<Food> It returns one Food with details.
 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
 * Created By-Siddharth Katule
 * Created Date -  02-12-2021 
 * 
 */
@GetMapping("/viewfood/{id}")
public ResponseEntity<Object> getFood(@PathVariable("id") int id) throws FoodIdNotFoundException {
	LOGGER.info("View Food Id");
	Map<String, Object> res = new HashMap<String, Object>();
	try {
		Optional<Food> p=serviceobj.viewFood(id);
	res.put("status", HttpStatus.OK.value());
	res.put("data", p);
	return new ResponseEntity<>(res, HttpStatus.OK);
	}catch(FoodIdNotFoundException e) {
		res.put("status", HttpStatus.NOT_FOUND.value());
		res.put("data", e.getMessage());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

}

/*
 * Method: UpdateFood
 * Description: It is used to update one food details from food table
 * @param food: food's reference variable
 * @returns String It returns a message whether the food is updated or not.
 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
 * Created By-Siddharth Katule
 * Created Date - 02-12-2021
 * 
*/
@PutMapping("/UpdateFood")
public ResponseEntity<String> UpdateFood(@RequestBody Food food) {
	LOGGER.info("Update Food");
	Food p= serviceobj.updateFood(food);
	if(p== null)
	return new ResponseEntity("Not Update!!", HttpStatus.NOT_FOUND);
	else
	return new ResponseEntity<>("Updated", HttpStatus.OK);

}


@GetMapping("/viewFoodList/{minCost}/{maxCost}")
public Optional<List<Food>> viewFoodList(@PathVariable("minCost") double minCost,@PathVariable("maxCost") double maxCost) throws InvalidMinCostException {
	LOGGER.info("View Food In the given range of minimum cost and maximum cost");
	return serviceobj.viewFoodList(minCost, maxCost);

}
}

