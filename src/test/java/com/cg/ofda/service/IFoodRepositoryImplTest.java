package com.cg.ofda.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.dao.IFoodRepositoryDao;
import com.cg.ofda.model.Food;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class IFoodRepositoryImplTest {
	@InjectMocks
	private IFoodServiceImpl service;
	@Mock
	private IFoodRepositoryDao dao;
	  
	/***************************************************************************************************************************
	 * Method: viewFoodListTest
	 * Description: It is used to test view all food method
	 
	
	 ***************************************************************************************************************************/


	@Test
	public void viewFoodListTest()
	{
		List<Food> p = new ArrayList<Food>();
		  
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("foodName");
	    food.setFoodType("foodType");
	    food.setFoodDescription("foodDescription");
	    food.setFoodCost(500.0);
	    food.setFoodQuantity(5);
	    p.add(food);
	   
		  
	    Food food2 = new Food();
	    food2.setFoodId(2);
	    food2.setFoodName("foodawsdrerName");
	    food2.setFoodType("foodtype");
	    food2.setFoodDescription("foodDeffddffscription");
	    food2.setFoodCost(500.0);
	    food2.setFoodQuantity(5);
	    p.add(food2);

		when(service.viewFoodList()).thenReturn(p); 
		assertEquals(2,service.viewFoodList().size());
		 
	}
	
	/***************************************************************************************************************************
	 * Method: getPizzaById 
	 * Description: It is used to test view one pizza method 
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	
	@Test
	public void getFoodById() throws FoodIdNotFoundException 
	{
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("FoodName");
	    food.setFoodType("FoodType");
	    food.setFoodDescription("FoodDescription");
	    food.setFoodCost(500.0);
	    food.setFoodQuantity(5);
	    
	    when(dao.existsById(food.getFoodId())).thenReturn(true);
	    Mockito.when(dao.findById(1)).thenReturn(Optional.of(food));
	    assertEquals(food.getFoodId(),service.viewFood(1).get().getFoodId());
		
	}
	
	/***************************************************************************************************************************
	 * Method: deleteFood
	 * Description: It is used to test delete one food method 
	
	 ***************************************************************************************************************************/

	
	@Test
	public void deleteFood() throws FoodIdNotFoundException
	{    when(dao.existsById(1000)).thenReturn(true);
		 Mockito.when(service.deleteFood(1000)).thenReturn("Deleted Food");
	        
	}
	
	/***************************************************************************************************************************
	 * Method: addFood 
	 * Description: It is used to test add one food method
	  
	 ***************************************************************************************************************************/

	 @Test
	 public void addFood()
	 {
		 List<Food> p = new ArrayList<Food>();
		  
		    Food food = new Food();
		    food.setFoodId(1);
		    food.setFoodName("foodName");
		    food.setFoodType("foodType");
		    food.setFoodDescription("foodDescription");
		    food.setFoodCost(500.0);
		    food.setFoodQuantity(5);
		    p.add(food);
		    
		    Food food2 = new Food();
		    food2.setFoodId(2);
		    food2.setFoodName("foodwsdrerName");
		    food2.setFoodType("foodTydffpe");
		    food2.setFoodDescription("foodDeffddffscription");
		    food2.setFoodCost(500.0);
		    food2.setFoodQuantity(5);
		    p.add(food2);
		    
		    when(service.addFood(food)).thenReturn(food);
		    when(service.addFood(food2)).thenReturn(food2);
		    assertEquals(2,p.size());
		    
		   
		    
	 }
	 
	 
	 /***************************************************************************************************************************
		 * Method: updateFood
		 * Description: It is used to test update one food method
		 
		 ***************************************************************************************************************************/

	 @Test
	 public void updateFood() throws FoodIdNotFoundException 
	 {
	  
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("foodName");
	    food.setFoodType("foodType");
	    food.setFoodDescription("foodDescription");
	    food.setFoodCost(500.0);
	    food.setFoodQuantity(5);
	  
	    when(dao.existsById(food.getFoodId())).thenReturn(true);
	    
	    food.setFoodId(1);
	    food.setFoodName("pizddzaName");
	    food.setFoodType("pizzaTyppe");
	    food.setFoodDescription("pizzaDescription");
	    food.setFoodCost(500.0);
	    food.setFoodQuantity(5);
	  
	    when(service.updateFood(food)).thenReturn(food);
	    assertEquals("pizddzaName", food.getFoodName());
	  
		 
	 }
	

}
