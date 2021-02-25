package com.app.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products_tbl")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	@Column(length = 30)
	private String proName;
	private float proPrice;
	@Lob
	private byte[] imageFile;
	@Column(length = 30)
	private String color;
	@Column(length = 30)
	private String imageContentType;
	private long proQuantity;
	@Column(length = 30)
	private String brandName;
	@Column(length = 30)
	private String categoryName;
	@JsonIgnore
	@OneToOne
	private OrderDetails orderdetails;
	@OneToMany(mappedBy = "product",cascade = CascadeType.MERGE)
	private List<CartItem> cartList=new ArrayList<>();
	public Product() {
		System.out.println("in product pojo.");
	}
	public Product(String proName, float proPrice, String color, long proQuantity,String brandName,String categoryName) {
		super();
		this.proName = proName;
		this.proPrice = proPrice;
		this.color = color;
		this.proQuantity = proQuantity;
		this.brandName = brandName;
		this.categoryName = categoryName;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public float getProPrice() {
		return proPrice;
	}
	public void setProPrice(float proPrice) {
		this.proPrice = proPrice;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getProQuantity() {
		return proQuantity;
	}
	public void setProQuantity(long proQuantity) {
		this.proQuantity = proQuantity;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	
	public OrderDetails getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(OrderDetails orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", proName=" + proName + ", proPrice=" + proPrice
				+ ", color=" + color + ", imageContentType=" + imageContentType + ", proQuantity=" + proQuantity
				+ ", brandName=" + brandName + ", categoryName=" + categoryName + "]";
	}
	
}
