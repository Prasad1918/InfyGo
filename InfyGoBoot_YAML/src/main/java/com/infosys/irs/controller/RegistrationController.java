package com.infosys.irs.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.service.RegistrationService;


@Controller
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private Environment environment;
	
	private String register="register";
	private String command="command";
	
	@GetMapping(value = "/register")
	public ModelAndView register(Model model) {
		return new ModelAndView(register, command, new User());
	}
	
	@PostMapping(value = "registerUser")
	public ModelAndView addCustomer(@Valid @ModelAttribute("command") User user, BindingResult result,
			ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		final  Logger logger ;
		
		if (result.hasErrors()) {
			modelAndView= new ModelAndView(register, command, user);
		} 
		else{
		try{
			registrationService.registerUser(user);
			modelAndView= new ModelAndView(register, command, user);
			modelAndView.addObject("successMessage",environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION"));
			
		}catch(UserIdAlreadyPresentException e){
			logger = LoggerFactory.getLogger(this.getClass());
			if (e.getMessage().contains("RegistrationService")) {
				modelAndView = new ModelAndView(register); 
				modelAndView.addObject(command,user);
				modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		}
		}
		return modelAndView;
	}
}