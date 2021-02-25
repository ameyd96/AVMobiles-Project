package com.app.controller;


import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.pojo.Product;
import com.app.pojo.User;
import com.app.service.IAdminservice;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminservice adminservice;
	
	public AdminController() {
		System.out.println("in admin controller ctor.");
	}
	
	@GetMapping("/userList")
	public ResponseEntity<?> getUser()
	{
		System.out.println("user list controller");
			List<User> users=adminservice.getUserList();
			if(users.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	@DeleteMapping("/{uid}")
	public void deleteUserDetails(@PathVariable int uid) {
		System.out.println("in del user dtls " + uid);
		try {
			adminservice.deleteUsers(uid);;
		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);
		}
		
	}
	@GetMapping("/proList")
	public ResponseEntity<?> getProducts()
	{
		System.out.println("product list controller");
			List<Product> products=adminservice.getAllProducts();
			if(products.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@RequestParam String proName,@RequestParam float proPrice
			,@RequestParam String color,@RequestParam long proQuantity,
			@RequestParam MultipartFile imageFile,@RequestParam String brandName,@RequestParam String categoryName)
	{
		System.out.println("addproduct");
		Product p = new Product(proName,proPrice,color,proQuantity,brandName,categoryName);
		{
		try {
			p.setImageFile(imageFile.getBytes());
			p.setImageContentType(imageFile.getContentType());
			String msg = adminservice.addProducts(p);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		catch (Exception e) 
		{
			return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}
	}
	}
	@PutMapping("/updateproduct/{pid}")
	public ResponseEntity<String> updateProduct(@RequestParam String proName,@RequestParam float proPrice,
			@RequestParam String color,@RequestParam long proQuantity,
			@RequestParam MultipartFile imageFile,@RequestParam String brandName,@RequestParam String categoryName,@PathVariable Integer pid)
	{
		System.out.println("updateproduct");
		Product p = new Product(proName,proPrice,color,proQuantity,brandName,categoryName);
		{
		try {
			p.setPid(pid);
			p.setImageFile(imageFile.getBytes());
			p.setImageContentType(imageFile.getContentType());
			return new ResponseEntity<String>(adminservice.updateProduct(p), HttpStatus.OK);
		}
		catch (Exception e) 
		{
			return new ResponseEntity<String>("not able to update reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}
	}
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Integer id)
	{
		System.out.println(" deleteProductByIdcontroller");
		try
		{
			return new ResponseEntity<String>(adminservice.delProduct(id), HttpStatus.OK);
		}
		catch (Exception e) 
		{
			return new ResponseEntity<String>("not able to delete = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}
	}
	
}
