package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.Product;
import com.app.service.IUserservice;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IUserservice userService;
	
	public ProductController() {
		System.out.println("in ctor of product controller.");
	}
	
	@GetMapping("/allproducts")
	public ResponseEntity<?> showProducts(HttpSession hs){
		System.out.println("product list");
		System.out.println(hs.getAttribute("validuser"));
			try
			{
				List<Product> list= userService.getAllProducts();
				return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
			}
			catch(RuntimeException e) {

				return new ResponseEntity<String>("not able to login user reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}
		
	@GetMapping("/showProduct/{pcategory}")
	public ResponseEntity<?> showProduct(@PathVariable String pcategory,HttpSession hs )
	{
		System.out.println("product list");
		System.out.println(hs.getAttribute("validuser"));
		try
		{
			List<Product> list= userService.showProductsByCategory(pcategory);
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
		catch(RuntimeException e)
		{
			return new ResponseEntity<String>("not able to login user reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}

