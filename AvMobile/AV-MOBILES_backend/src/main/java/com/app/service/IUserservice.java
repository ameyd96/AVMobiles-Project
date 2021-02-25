package com.app.service;

import java.util.List;

import com.app.pojo.CartItem;
import com.app.pojo.Product;
import com.app.pojo.User;

public interface IUserservice {

	public User authenticateUser(String email, String password);
	public User addUser(User user);
	public List<Product> getAllProducts();
	public List<Product> showProductsByCategory(String category);
	public List<CartItem> getAllCartItems(int id);
	public Integer addCartItems(int uid, int pid, int quant);
	public void deleteFromCart(int cartId);
	public Product getProductById(int userId);
	public User getUserById(int uid);
}
