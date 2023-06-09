package com.infosys.irs.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;



public class Password {
	@NotNull(message = "Email must not be blank.")
	@Email
	private String email;
	
	@NotNull(message = "PhoneNumber must not be blank.")
	@Size(min = 10, max = 10, message = "PhoneNumber must be 10 digits.")
	private String phone;

	@NotNull(message = "Please enter your password.")
	@Size(min=8,max=15 , message = "Password must be between 8 and 15 characters")
	private String newPassword;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	

}
