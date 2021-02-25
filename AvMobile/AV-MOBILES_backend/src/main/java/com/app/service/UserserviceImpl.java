package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.IUserdao;
import com.app.pojo.CartItem;
import com.app.pojo.Product;
import com.app.pojo.User;
@Service
@org.springframework.transaction.annotation.Transactional
public class UserserviceImpl implements IUserservice {
	
	@Autowired
	private IUserdao userdao;

	public UserserviceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("in user service ctor.");
	}
	@Override
	public User authenticateUser(String email, String password) {
		
		return userdao.validateUser(email, password);
	}
	@Override
	public User addUser(User user) {
		
		return userdao.registerUser(user);
	}
	@Override
	public List<Product> getAllProducts() {
		
		return userdao.getAllProductCustomer();
	}
	@Override
	public List<Product> showProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return userdao.getProductsByCategory(category);
	}
	@Override
	public List<CartItem> getAllCartItems(int id) {
		
		return userdao.getAllCartItems(id);
	}
	@Override
	public Integer addCartItems(int uid, int pid, int quant) {
		// TODO Auto-generated method stub
		return userdao.addtoCartItems(uid, pid, quant);
	}
	@Override
	public void deleteFromCart(int cartId) {
		// TODO Auto-generated method stub
		userdao.deleteCartItems(cartId);
	}
	@Override
	public Product getProductById(int userId) {
		// TODO Auto-generated method stub
		return userdao.getProduct(userId);
	}
	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return userdao.getUserById(uid);
	}
}
