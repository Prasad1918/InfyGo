
package com.infosys.irs.api;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.infosys.irs.exception.InfyGoBootException;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.utility.CalendarUtility;

@RestController
@RequestMapping("FlightAPI")
public class FlightAPI {

	@Autowired
	private FlightService flightService;

	@GetMapping(value = "/{source}/{destination}/{journeyDate}", headers = "Accept=application/json")
	public List<SearchFlights> searchFlights(@PathVariable String source, @PathVariable String destination,
			@PathVariable String journeyDate) throws InfyGoBootException, ParseException {
		Calendar jDate = CalendarUtility.getCalendarFromString(journeyDate);

		return flightService.getFlights(source, destination, jDate);
	}

	@GetMapping(value = "/source", headers = "Accept=application/json")
	public List<String> getSources() throws InfyGoBootException {
		try {

			return flightService.getSources();
		} catch (Exception e) {

			throw e;
		}
	}

	@GetMapping(value = "/destination", headers = "Accept=application/json")
	public ResponseEntity<List<String>> getDestinations() throws InfyGoBootException {
		List<String> s2 = flightService.getDestinations();
		return new ResponseEntity<List<String>>(s2, HttpStatus.OK);
	}
}
