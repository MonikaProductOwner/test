package com.cg.ofda.service;

import java.util.List;
import java.util.Optional;

import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.Exceptions.InvalidMinCostException;
import com.cg.ofda.model.Food;

public interface IFoodService {

	
	Food addFood(Food food);

	Food updateFood(Food food) throws FoodIdNotFoundException;

	String deleteFood(int foodId) throws FoodIdNotFoundException; //throws FoodIdNotFoundException;

	Optional<Food> viewFood(int foodId) throws FoodIdNotFoundException; //throws FoodIdNotFoundException;

	List<Food> viewFoodList();

	Optional<List<Food>> viewFoodList(double minCost, double maxCost) throws InvalidMinCostException;//throws InvalidMinCostException;
}


