package com.cg.ofda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food {
	public Food() {
		
	}
	@Id
	@GeneratedValue
	private int foodId;
	private String foodType;
	private String foodName;
	private String foodDescription;
	private double foodCost;
	private int foodQuantity;
	public Food(int foodId, String foodType, String foodName, String foodDescription, double foodCost,
			int foodQuantity) {
		super();
		this.foodId = foodId;
		this.foodType = foodType;
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.foodCost = foodCost;
		this.foodQuantity = foodQuantity;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodDescription() {
		return foodDescription;
	}
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}
	public double getFoodCost() {
		return foodCost;
	}
	public void setFoodCost(double foodCost) {
		this.foodCost = foodCost;
	}
	public int getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	
	@Override
	public String toString()
	{
		return foodId+" "+foodName+" "+foodType+" "+foodDescription+" "+foodCost+" "+foodQuantity;
		
	}
	
}

	