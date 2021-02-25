package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojo.Product;
import com.app.pojo.User;

@Repository
public class AdmindaoImpl implements IAdmindao {

	@Autowired
	private EntityManager manager;
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String jpql = "select u from User u";
		return manager.createQuery(jpql, User.class).getResultList();
	
	}
	@Override
	public void deleteUser(int pid) {
		// TODO Auto-generated method stub
		User u = manager.find(User.class, pid);
		manager.remove(u);
	}
	@Override
	public List<Product> getAllProducts() {
		String jpql = "select p from Product p";
		return manager.createQuery(jpql, Product.class).getResultList();
	}
	@Override
	public String addProduct(Product p) {
		String msg = "prouduct adding success.";
		manager.persist(p);
		return msg;
	}
	
	@Override
	public String updateProduct(Product p) {
		Product pro = manager.merge(p);
		if(pro!=null)
		  return "product updated succesfully";
		return "product not updated.";
	}
	@Override
	public String deleteProduct(int pid) {
		String msg = "product del failed.";
		Product p = manager.find(Product.class ,pid);
		if(p!=null) {
		   manager.remove(p);
		   msg = "product del succesfully.";
		}
		return msg;
	}


}
