package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IAdmindao;
import com.app.pojo.Product;
import com.app.pojo.User;

@Service
@Transactional
public class AdminserviceImpl implements IAdminservice {

	@Autowired
	IAdmindao dao;
	
	public AdminserviceImpl() {
		System.out.println("in admin service ctor.");
	}
	
	@Override
	public List<User> getUserList() {
		return dao.getAllUser();
	}

	@Override
	public void deleteUsers(int uid) {
		dao.deleteUser(uid);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return dao.getAllProducts();
	}

	@Override
	public String addProducts(Product p) {
		
		return dao.addProduct(p);
	}


	@Override
	public String updateProduct(Product pro) {
	  
		return dao.updateProduct(pro);
	}

	@Override
	public String delProduct(int pid) {
		
		return dao.deleteProduct(pid);
	}

	
}
