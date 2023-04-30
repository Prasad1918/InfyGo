/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;



import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.exception.InfyGoBootException;
import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.exception.SeatsNotAvaialbleException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.BookingService;

@Controller
@SessionAttributes({"booking","userId"})
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Environment environment;
	private String booking="booking";



	@GetMapping(value = "/bookFlight")
	public ModelAndView proceed(ModelMap model,	@RequestParam("flightid") String flightId, HttpSession httpSession, SessionStatus status) throws InfyGoBootException {
		status.setComplete();
		SearchFlights flight = bookingService.getFlightDetails(flightId);

		Booking bookingObj = new Booking();
		bookingObj.setDepartureDate(flight.getFlightAvailableDate());
		bookingObj.setDepartureTime(flight.getDepartureTime());
		bookingObj.setDestination(flight.getDestination());
		bookingObj.setFare(flight.getFare());
		bookingObj.setFlightId(flightId);
		bookingObj.setSource(flight.getSource());
		bookingObj.setAirlines(flight.getAirlines());
		bookingObj.setSeats(Integer.parseInt(flight.getSeatCount()));
		bookingObj.setName((String)httpSession.getAttribute("userId"));


		model.addAttribute(booking,booking);

		return new ModelAndView("bookingReview", "command", booking);

	}





	@GetMapping(value = "/bookingProcess")
	public ModelAndView bookingProcess1(
			ModelMap model,  HttpSession session)  throws InfyGoBootException{
		
		PassengerListContainer passengerListContainer = (PassengerListContainer)session.getAttribute("passengerListContainer");


		Booking newBooking = (Booking)session.getAttribute(booking);
		ModelAndView modelAndView = new ModelAndView("payment", "command", new CreditCard());

		try {
			newBooking = bookingService.bookTicket(newBooking, passengerListContainer);

			session.setAttribute(booking, newBooking);
			modelAndView.addObject("booking",newBooking);
		} catch (SeatsNotAvaialbleException | PassengerDetailNotFoundException  e) {

			modelAndView = new ModelAndView("addPassengers"); 	
			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
		}
		
	
		return modelAndView;


	}

}
