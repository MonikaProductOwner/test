package com.cg.ofda.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="orderinfom")
public class Order 
{
        @Id	
        @GeneratedValue
	  	private int orderId;
              	private double finalPrice;
		public double getFinalPrice() {
			return finalPrice;
		}

		public void setFinalPrice(double finalPrice) {
			this.finalPrice = finalPrice;
		}

		
		private String orderStatus;
        		
	    @ManyToOne
		@JoinColumn(name="customerId")
		private Customer customer;
		
		
		public Customer getCustomer(){
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

			
		@OneToOne
		@JoinColumn(name="cartId",nullable=false)
		private Cart cart;
		
		public Cart getCart() {
		return cart;
		}

		public void setCart(Cart cart) {
			this.cart = cart;
		}

		
        //The property value will be set to the current VM date exactly once when saving the owning entity for the first time.
		@Column(name="orderdate")
		@CreationTimestamp
		private LocalDate orderDate;
		
		public LocalDate getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDate orderDate) {
			this.orderDate = orderDate;
		}

		public int getOrderId() 
		{
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		


	}
	

