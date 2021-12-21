package com.cg.ofda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ofda.model.Food;

@Repository

public interface IFoodRepositoryDao extends JpaRepository<Food, Integer> {

		@Query("select p from Food  p where p.foodCost>?1 and p.foodCost<?2")
		Optional<List<Food>> viewFoodList(double minCost, double maxCost);
		
		@Query("select max(foodCost) from Food") 
		double MaxFood();

		
		String deleteById(int foodId);
	}


