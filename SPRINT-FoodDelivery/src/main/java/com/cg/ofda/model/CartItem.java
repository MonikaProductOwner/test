package com.cg.ofda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartitems")
public class CartItem {
	
	@Id
	@GeneratedValue
	private int itemId;
	@OneToOne
	@JoinColumn(name = "foodId", nullable=false)
	private Food food;
	
	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", food=" + food + ", fooditem=" + fooditem + ", quantity=" + quantity
				+ ", customer=" + customer + "]";
	}
	
private String fooditem;
@Column(nullable=true)
private int quantity;
@ManyToOne
@JoinColumn(name = "customerId", nullable=false)
private Customer customer;
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}

public Food getFood() {
	return food;
}
public void setFood(Food food) {
	this.food = food;
}
public String getFoodItem() {
	return fooditem;
}
public void setFoodItem(String fooditem) {
	this.fooditem = fooditem;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}


}



