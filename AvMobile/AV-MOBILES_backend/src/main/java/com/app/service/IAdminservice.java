package com.app.service;

import java.util.List;

import com.app.pojo.Product;
import com.app.pojo.User;

public interface IAdminservice {

	public List<User> getUserList();
	public void deleteUsers(int uid);
	public List<Product> getAllProducts();
	public String addProducts(Product p);
	public String updateProduct(Product pro);
	public String delProduct(int pid);
}
