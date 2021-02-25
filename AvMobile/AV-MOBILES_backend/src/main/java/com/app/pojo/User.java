package com.app.pojo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="user")
@JsonInclude(Include.NON_DEFAULT)
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 10)
	private String gender;
	@Column(length = 30,unique = true)
	@Email(message = "Invalid Email format")
	private String email;
	@Column(length = 30)
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,10})",message = "Blank Or Invalid Password ")
	private String password;
	@Transient
	@Column(length = 30)
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,10})",message = "Blank Or Invalid Password ")
	private String confirmPassword;
	@Column(length = 10)
	//@Pattern(regexp = "^[0][1-9]{1}[0-9]{9}$")
	private String phone;
	@Enumerated(EnumType.STRING)
	@Column(name="user_role",length = 20)
	private Role userRole;
	@Lob
	private byte[] image;
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	public User()
	{
		System.out.println("in user ctor.");
	}
	public User(@NotBlank(message = "name must be supplied.") String firstName,
			@NotBlank(message = "name must be suppplied.") String lastName,
			@NotBlank(message = "gender must be supplied.") String gender,
			@NotBlank(message = "Email can't be blank") @Email(message = "Invalid Email format") String email,
			@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,10})", message = "Blank Or Invalid Password ") String password,
			@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,10})", message = "Blank Or Invalid Password ") String confirmPassword,
			@Pattern(regexp = "^[0][1-9]{1}[0-9]{9}$") String phone, Role userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phone = phone;
		this.userRole = userRole;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", phone="
				+ phone + ", userRole=" + userRole + "]";
	}
	
	
}
