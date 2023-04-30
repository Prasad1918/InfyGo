/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Passenger implements Serializable{
	
	private static final long serialVersionUID = 6265895816693882368L;
	
	@NotNull(message = "Passenger name must not be blank")
	@Size(min = 2, max = 15, message = "Passenger name must be between 2 to 15 Characters")
    private String passengerName;
	@NotNull(message = "Age must not be blank")
    private Integer age;
	@NotNull(message = "Gender must not be blank")
    private String gender;

    public Passenger() {
    }

    public Passenger(String name, Integer age, String gender) {
        this.passengerName = name;
        this.age = age;
        this.gender = gender;
    }

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    
}
