package com.app.dao;

import java.util.List;

import com.app.pojo.CartItem;
import com.app.pojo.Product;
import com.app.pojo.User;

public interface IUserdao {
	public User validateUser(String email,String password);
	public User registerUser(User user);
	public List<Product> getAllProductCustomer();
	public List<Product> getProductsByCategory(String category);
	public List<CartItem> getAllCartItems(int id);
	public Integer addtoCartItems(int uid, int pid,int quant);
	public void deleteCartItems(int cartId);
	public Product getProduct(int uid);
	public Product getProductFromCart(int cid);
	public User getUserById(int uid);
}
