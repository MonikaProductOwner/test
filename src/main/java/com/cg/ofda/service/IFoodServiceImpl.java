package com.cg.ofda.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.Exceptions.FoodIdNotFoundException;
import com.cg.ofda.Exceptions.InvalidMinCostException;
import com.cg.ofda.dao.IFoodRepositoryDao;
import com.cg.ofda.model.Food;

@Service
@Transactional

public class IFoodServiceImpl implements IFoodService{
	
	@Autowired
	IFoodRepositoryDao dao;


@Override
public Food addFood(Food food) {
	Food p = dao.saveAndFlush(food);
	return p;
}


@Override
public String deleteFood(int foodId) throws FoodIdNotFoundException {

	if (!dao.existsById(foodId)) {
		throw new FoodIdNotFoundException("No Food found for the user id");
	} else {
		dao.deleteById(foodId);
		return "Deleted Food";
	}
}



@Override
public Optional<Food> viewFood(int foodId) throws FoodIdNotFoundException // throws FoodIdNotFoundException
{
	if (!dao.existsById(foodId)) {
		throw new FoodIdNotFoundException("No Food found for the user id");
	} else {
		Optional<Food> p = dao.findById(foodId);
		System.out.println("Getting data from db" + p);
		return p;
	}
}


@Override
public List<Food> viewFoodList() {
	List<Food> p = dao.findAll();
	System.out.println("Getting data from db " + p);
	return p;
}


@Override
public Optional<List<Food>> viewFoodList(double minCost, double maxCost) throws InvalidMinCostException {
	return dao.viewFoodList(minCost, maxCost);
}



@Override
public Food updateFood(Food food) {
	if (!dao.existsById(food.getFoodId())) {
		return null;
	} else {
		dao.save(food);
		return dao.save(food);
	}
}

}