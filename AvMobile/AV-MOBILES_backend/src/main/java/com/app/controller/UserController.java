package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.CartItem;
import com.app.pojo.Role;
import com.app.pojo.User;
import com.app.service.IUserservice;
import com.app.service.Notificationservice;
import com.sun.mail.iap.Response;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserservice userService;
	private Notificationservice notification;
	 
	public UserController() {
		System.out.println("in ctor of user controller.");
		notification=new Notificationservice();
	}
	@GetMapping
    public ResponseEntity<?> test(){
    	System.out.println("in test method");
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User u, HttpSession hs) {
		System.out.println("user login");
		try {
			User user = userService.authenticateUser(u.getEmail(), u.getPassword());
			hs.setAttribute("validuser", user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("not able to login user reason = " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u) {
		System.out.println("register user");
		String sub = "Registration Successful!.";
		String msg = "Welcome "+u.getFirstName()+". you have successfully registered.";
		try {
			u.setUserRole(Role.CUSTOMER);
			User user = userService.addUser(u);
			notification.sendMail(user.getEmail(), sub, msg);
		return new ResponseEntity<>("registration succesfull. "+user.getId(), HttpStatus.OK);
		}
		catch (RuntimeException e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		catch (Exception e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}

	}
	@GetMapping("/logout")
	public ResponseEntity<?> logoutUser(HttpSession hs){
		System.out.println("in logout");
		if(hs!=null) {
			hs.invalidate();
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/cartitems/{uid}")
	public ResponseEntity<?> showCartItems(@PathVariable int uid){
		System.out.println("in get cart items");
		
		try {
			return new ResponseEntity<List<CartItem>>(userService.getAllCartItems(uid), HttpStatus.OK) ;
		}
		catch (RuntimeException e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	@GetMapping("/addToCart/{uid}/{productId}/{quant}")
	public ResponseEntity<?> addToCart(@PathVariable int uid, @PathVariable int productId,@PathVariable int quant){
		System.out.println("in add to cart method");
		
		try {
			return new ResponseEntity<>(userService.addCartItems(uid, productId, quant), HttpStatus.OK) ;
		}
		catch (RuntimeException e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	@DeleteMapping("/delcart/{cartid}")
	public void delefromCart(@PathVariable int cartid){
		System.out.println("in del cart contoller");
		
		try {
		     userService.deleteFromCart(cartid);
		    
		}
		catch (RuntimeException e) 
		{
			System.out.println("cart not deleted"+e.getMessage());
		}
		
	}
	@GetMapping("/getProductById/{pid}")
	public ResponseEntity<?> getProductById(@PathVariable int pid)
	{
		System.out.println("in getProductById cart contoller");
		try {
			return new ResponseEntity<>(userService.getProductById(pid), HttpStatus.OK) ;
		}
		catch (RuntimeException e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	@GetMapping("/placeorder/{uid}/{totalPrice}")
	public ResponseEntity<?> placeOrder(@PathVariable int uid,@PathVariable int totalPrice){
		System.out.println("in place order method.");
		User u = userService.getUserById(uid);
		String to = u.getEmail();
		String sub = "Order confirmation.";
		String msg = "Hi!, "+u.getFirstName()+" Your order has been confirmed!!. the total bill amount is: "+totalPrice;
		try {
			notification.sendMail(to, sub, msg);
			return new ResponseEntity<>("order placed succesfull.", HttpStatus.OK);
		}
		catch (Exception e) 
		{
		return new ResponseEntity<String>("not able to register reason = "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	
}
