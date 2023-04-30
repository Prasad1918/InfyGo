/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.model.Passenger;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.service.PassengerService;

@Controller
@SessionAttributes({ "passengerListContainer", "booking" })
public class PassengerDetailsController {
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private Environment environment;
	private String passengerListContainer = "passengerListContainer";

	@GetMapping("/addPassengerDetails")
	public String addPassengers(ModelMap map, HttpSession session, HttpServletRequest request, HttpSession httpSession,
			SessionStatus status, @RequestParam(value = "f", required = false) String flush)

	{

		if (flush != null) {

			session.setAttribute(passengerListContainer, getDummyPassengerListContainer());
		}
		if (session.getAttribute(passengerListContainer) == null) {

			session.setAttribute(passengerListContainer, getDummyPassengerListContainer());
		}
		map.addAttribute(passengerListContainer, (PassengerListContainer) session.getAttribute(passengerListContainer));

		session.setAttribute("contextPath", request.getContextPath());

		status.setComplete();
		return "addPassengers";
	}

	@PostMapping(value = "/editPassengerListContainer")
	public String editpersonListContainer(@ModelAttribute PassengerListContainer personListContainer,
			HttpSession session, ModelMap map

	) {
		String returnValue = "redirect:/bookingProcess";
		List<Passenger> passenger = personListContainer.getPassengerList();
		session.setAttribute(passengerListContainer, personListContainer);

		try {

			passengerService.validatePassengerDetails(passenger);

		} catch (PassengerDetailNotFoundException e) {

			map.addAttribute("message", environment.getProperty(e.getMessage()));
			returnValue = "addPassengers";

		}
		return returnValue;

	}

	private PassengerListContainer getDummyPassengerListContainer() {
		List<Passenger> passengerList = new ArrayList<Passenger>();
		for (int i = 0; i < 1; i++) {

			passengerList.add(new Passenger());
		}
		return new PassengerListContainer(passengerList);
	}
}
