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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.exception.SeatsNotAvailableException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.BookingService;
import com.infosys.irs.utility.ApplicationConstants;

@Controller
@ResponseBody
@SessionAttributes({ "booking", "userId" })
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Environment environment;

	@GetMapping("/bookFlight")
	public ModelAndView proceed(ModelMap model, @RequestParam("flightid") String flightId, HttpSession httpSession,
			SessionStatus sessionStatus) {
		SearchFlights flight = bookingService.getFlightDetails(flightId);

		Booking booking = new Booking();
		booking.setDepartureDate(flight.getFlightAvailableDate());
		booking.setDepartureTime(flight.getDepartureTime());
		booking.setDestination(flight.getDestination());
		booking.setFare(flight.getFare());
		booking.setFlightId(flightId);
		booking.setSource(flight.getSource());
		booking.setAirlines(flight.getAirlines());
		booking.setSeats(Integer.parseInt(flight.getSeatCount()));
		booking.setName((String) httpSession.getAttribute("userId"));

		model.addAttribute(ApplicationConstants.BOOKING, booking);

		sessionStatus.setComplete();

		return new ModelAndView("bookingReview", "command", booking);
	}

	@GetMapping("/bookingProcess")
	public ModelAndView bookingProcess1(ModelMap model, HttpSession session) {

		PassengerListContainer passengerListContainer = (PassengerListContainer) session
				.getAttribute("passengerListContainer");

		Booking newBooking = (Booking) session.getAttribute(ApplicationConstants.BOOKING);
		ModelAndView modelAndView = new ModelAndView("payment", "command", new CreditCard());

		try {
			newBooking = bookingService.bookTicket(newBooking, passengerListContainer);

			session.setAttribute(ApplicationConstants.BOOKING, newBooking);
			modelAndView.addObject(ApplicationConstants.BOOKING, newBooking);
		} catch (SeatsNotAvailableException | PassengerDetailNotFoundException e) {
			modelAndView = new ModelAndView("addPassengers");
			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
		}

		return modelAndView;

	}

}
