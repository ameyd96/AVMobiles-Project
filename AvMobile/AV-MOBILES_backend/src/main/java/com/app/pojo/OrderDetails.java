package com.app.pojo;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ordered_products")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int quantity;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pro_id")
	private Product product;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ord_id")
	private Order order;
	
	public OrderDetails() {
		System.out.println("in ctor of order details.");
	}

	public OrderDetails(int quantity) {
		super();
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
    
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", quantity=" + quantity + ", product=" + product + ", order=" + order + "]";
	}
	
	
}
