/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.infosys.irs.exception.InfyGoBootException;

import com.infosys.irs.service.FlightService;

@RestController
@RequestMapping("FlightAPI")
public class FlightAPI {

	@Autowired
	private FlightService flightService;

	@GetMapping(value = "/source", headers = "Accept=application/json")
	public List<String> getSources() throws InfyGoBootException {
		List<String> s1;
		try {
			s1 = flightService.getSources();
		} catch (Exception e) {

			throw e;
		}
		return s1;
	}

	@GetMapping(value = "/destination", headers = "Accept=application/json")
	public List<String> getDestinations() throws InfyGoBootException {

		return flightService.getDestinations();
	}
}
