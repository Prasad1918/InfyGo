/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TICKET_DETAILS")
public class TicketEntity {
@Id
private String pnr;
@Column(name="bookingdate")
private String bookingDate;
@Column(name="departuredate")
private String departureDate;
@Column(name="departuretime")
private String departureTime;
@Column(name="totalfare")
private String totalFare;
@OneToOne(cascade = CascadeType.REFRESH)
@JoinColumn(name="flightid")
private FlightEntity flight;
@JoinColumn(name="userid")
@OneToOne(cascade = CascadeType.REFRESH)
private UserEntity customer;
@Column(name="noofseats")
private int noOfSeats;
public String getPnr() {
	return pnr;
}
public void setPnr(String pnr) {
	this.pnr = pnr;
}
public String getBookingDate() {
	return bookingDate;
}
public void setBookingDate(String bookingDate) {
	this.bookingDate = bookingDate;
}
public String getDepartureDate() {
	return departureDate;
}
public void setDepartureDate(String departureDate) {
	this.departureDate = departureDate;
}
public String getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(String departureTime) {
	this.departureTime = departureTime;
}
public String getTotalFare() {
	return totalFare;
}
public void setTotalFare(String totalFare) {
	this.totalFare = totalFare;
}

public FlightEntity getFlight() {
	return flight;
}
public void setFlight(FlightEntity flight) {
	this.flight = flight;
}
public UserEntity getCustomer() {
	return customer;
}
public void setCustomer(UserEntity customer) {
	this.customer = customer;
}
public int getNoOfSeats() {
	return noOfSeats;
}
public void setNoOfSeats(int noOfSeats) {
	this.noOfSeats = noOfSeats;
}


}
