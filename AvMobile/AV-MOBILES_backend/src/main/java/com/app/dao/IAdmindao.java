package com.app.dao;

import java.util.List;


import com.app.pojo.Product;
import com.app.pojo.User;

public interface IAdmindao {

	public List<User> getAllUser();
	public void deleteUser(int pid);
	public List<Product> getAllProducts();
	public String addProduct(Product p);
	public String updateProduct(Product p);
	public String deleteProduct(int pid);
}
