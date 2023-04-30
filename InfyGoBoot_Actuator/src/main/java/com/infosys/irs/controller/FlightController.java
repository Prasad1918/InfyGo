/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.exception.FlightNotAvailableException;
import com.infosys.irs.exception.InvalidJourneyDateException;
import com.infosys.irs.exception.InvalidSourceDestinationException;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.utility.ApplicationConstants;
import com.infosys.irs.utility.CalendarUtility;

@Controller
@ResponseBody
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private Environment environment;

	@GetMapping("/flights")
	public ModelAndView getFlights(ModelMap model) {
		ModelAndView modelAndView = null;

		List<String> s1 = flightService.getSources();

		model.addAttribute(ApplicationConstants.SOURCE_LIST, s1);

		// Select destination values from db
		List<String> s2 = flightService.getDestinations();

		model.addAttribute(ApplicationConstants.DESTINATION_LIST, s2);

		modelAndView = new ModelAndView(ApplicationConstants.SEARCH_FLIGHTS, ApplicationConstants.COMMAND, new SearchFlights());
		return modelAndView;
	}

	@GetMapping("/searchFlights")
	public ModelAndView searchFlights(@Valid @ModelAttribute("command") SearchFlights searchFlights,
			BindingResult bindingResult, ModelMap model) throws ParseException {
		
		ModelAndView modelAndView = null;
		
		try {
			if (bindingResult.hasErrors()) {
				modelAndView = new ModelAndView(ApplicationConstants.SEARCH_FLIGHTS, ApplicationConstants.COMMAND, searchFlights);

			} else {

				String source = searchFlights.getSource();
				String destination = searchFlights.getDestination();

				Calendar journeyDate = CalendarUtility.getCalendarFromString(searchFlights.getJourneyDate());

				List<SearchFlights> availableFlights = flightService.getFlights(source, destination, journeyDate);

				model.addAttribute("availableFlights", availableFlights);
				model.addAttribute("size", availableFlights.size());

				modelAndView = new ModelAndView(ApplicationConstants.SEARCH_FLIGHTS, ApplicationConstants.COMMAND, searchFlights);
			}
		} catch (FlightNotAvailableException | InvalidJourneyDateException | InvalidSourceDestinationException e) {
			if (e.getMessage().contains("FlightService")) {
				modelAndView = new ModelAndView("searchFlights");

			}
			if(modelAndView!=null)
			{
			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			}
		}
		List<String> s1 = flightService.getSources();
		model.addAttribute(ApplicationConstants.SOURCE_LIST, s1);

		// Select destination values from db
		List<String> s2 = flightService.getDestinations();
		model.addAttribute(ApplicationConstants.DESTINATION_LIST, s2);
		return modelAndView;
	}

	@GetMapping("/modifyBooking")
	public ModelAndView authenticateLogin(ModelMap model) {

		// Select source values from db
		List<String> s1 = flightService.getSources();

		model.addAttribute(ApplicationConstants.SOURCE_LIST, s1);

		// Select destination values from db
		List<String> s2 = flightService.getDestinations();

		model.addAttribute(ApplicationConstants.DESTINATION_LIST, s2);

		return new ModelAndView(ApplicationConstants.SEARCH_FLIGHTS, ApplicationConstants.COMMAND, new SearchFlights());

	}

}
