package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojo.CartItem;
import com.app.pojo.Product;
import com.app.pojo.User;
@Repository
public class UserdaoImpl implements IUserdao {

	@Autowired
	private EntityManager manager;
	
	public  UserdaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("in user dao ctor.");
	}
	@Override
	public User validateUser(String email, String password) {
		System.out.println("in user dao validateUsr()");
		User u = null;
		String jpql = "select u from User u  where u.email=:em and u.password=:pass";
		u = manager.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", password).getSingleResult();
		return u;
	}
	@Override
	public User registerUser(User user) {
		manager.persist(user);
		return user;
		
	}
	@Override
	public List<Product> getAllProductCustomer() {
		System.out.println("in get product dao");
		String jpql = "select p from Product p";
		List<Product> products = manager.createQuery(jpql, Product.class).getResultList();
		return products;
	}
	@Override
	public List<Product> getProductsByCategory(String category) {
		String jpql = "select p from Product p where p.categoryName=:cat";
		List<Product> products = manager.createQuery(jpql, Product.class).setParameter("cat",category).getResultList();
		if(products==null)
			return null;
		return products;
	}
	@Override
	public List<CartItem> getAllCartItems(int id) {
        String jpql = "select c from CartItem c where c.user.id=:Id";
        List<CartItem> cartitems = manager.createQuery(jpql, CartItem.class).setParameter("Id", id).getResultList();
		return cartitems;
	}
	@Override
	public Integer addtoCartItems(int uid, int pid, int quant) {
		User user  = manager.find(User.class, uid);
		Product p = manager.find(Product.class, pid);
		CartItem cartItem = new CartItem(quant);
		cartItem.setProduct(p);
		cartItem.setUser(user);
		manager.persist(cartItem);
		return cartItem.getId();
	}
	@Override
	public void deleteCartItems(int cartId) {
		CartItem cartItem = manager.find(CartItem.class, cartId);
		manager.remove(cartItem);
	}
	@Override
	public Product getProduct(int uid) {
		
		return manager.find(Product.class, uid);
	}
	@Override
	public Product getProductFromCart(int cid) {
		CartItem c=manager.find(CartItem.class, cid);
		return c.getProduct();
	}
	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return manager.find(User.class, uid);
	}
}
