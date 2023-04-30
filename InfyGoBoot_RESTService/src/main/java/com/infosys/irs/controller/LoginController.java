/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.InvalidCredentialException;
import com.infosys.irs.model.Login;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.service.FlightService;
import com.infosys.irs.service.LoginService;

@Controller
@SessionAttributes({"userName","userId"})


public class LoginController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private FlightService flightService;
	@Autowired
	private Environment environment;
	private String command="command";
	private String login="login";


	@GetMapping(value = "/login")
	public ModelAndView getLoginDetails(@RequestParam Optional<String> error, SessionStatus status) {
		
		status.setComplete();
	
		return new ModelAndView(login,command,new Login()); 
	}

	@PostMapping(value = "/authenticateLogin")
	public ModelAndView authenticateLogin(@Valid @ModelAttribute("command") Login userLogin, BindingResult result,
			ModelMap model, SessionStatus status) {		
		logger.info("In authenticateLogin controller");
		status.setComplete();
		ModelAndView modelAndView = new ModelAndView("error");


		try {
			if (result.hasErrors()) {

				modelAndView= new ModelAndView(login, command, userLogin);

			}
			else{

				UserEntity userEntity = loginService.authenticateLogin(userLogin);

				model.addAttribute("userName", userEntity.getName());
				model.addAttribute("userId", userEntity.getUserId());
                //Select source values from db
                List<String> s1=flightService.getSources();
                
                model.addAttribute("sourceList", s1);
                
                //Select destination values from db
                List<String> s2=flightService.getDestinations();
               
                model.addAttribute("destinationList", s2);

			modelAndView = new ModelAndView("searchFlights", command, new SearchFlights());	
				

			}
		}
		catch (InvalidCredentialException e) {
			
			if (e.getMessage().contains("LoginService")) {
				modelAndView = new ModelAndView(login); 
				modelAndView.addObject("loginName", userLogin.getUserName());
			}

			modelAndView.addObject("message", environment.getProperty(e.getMessage()));
		}
		return modelAndView;

	}
	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpSession session,SessionStatus status) {
		status.setComplete();
		ModelAndView model = new ModelAndView("infyGoHome", "", "");

		Enumeration<String> attributes = session.getAttributeNames();

		while (attributes.hasMoreElements())
			session.removeAttribute(attributes.nextElement());
		
		model.addObject("logoutMessage", environment.getProperty("LoginController.LOGOUT_SUCCESS"));
		return model;
	}

}
