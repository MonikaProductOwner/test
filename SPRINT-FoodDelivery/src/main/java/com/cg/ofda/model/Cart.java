package com.cg.ofda.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartm")

public class Cart {

		@Id
		@GeneratedValue
		private int cartId;
		@OneToOne
		@JoinColumn(name = "customerId", nullable=false)
		private Customer customer;
		@Column(nullable = true)
		private double total;
		
		@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
		@JoinColumn(name = "cart")
		private List<CartItem> cartItem;
		
		public List<CartItem> getCartItem() {
			return cartItem;
		}

		public void setCartItem(List<CartItem> cartItem) {
			this.cartItem = cartItem;
		}

		public int getCartId() {
			return cartId;
		}

		public void setCartId(int cartId) {
			this.cartId = cartId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}
}


