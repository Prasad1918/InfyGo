
  package com.infosys.irs.controller;
  
  import javax.validation.Valid;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.context.annotation.Bean; import
  org.springframework.core.env.Environment; import
  org.springframework.jms.core.JmsTemplate; import
  org.springframework.jms.support.converter.MappingJackson2MessageConverter;
  import org.springframework.jms.support.converter.MessageConverter; import
  org.springframework.jms.support.converter.MessageType; import
  org.springframework.stereotype.Controller; import
  org.springframework.ui.Model; import org.springframework.ui.ModelMap; import
  org.springframework.validation.BindingResult; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.ModelAttribute; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.servlet.ModelAndView;
  
  import com.infosys.irs.exception.UserIdAlreadyPresentException; import
  com.infosys.irs.model.Email; import com.infosys.irs.model.User; import
  com.infosys.irs.service.RegistrationService; import
  com.infosys.irs.utility.ApplicationConstants;
  
  @Controller public class RegistrationController {
  
  @Autowired private RegistrationService registrationService;
  
  @Autowired private Environment environment;
  
 // @Autowired 
  //private JmsTemplate jmsTemplate;
  
  @GetMapping("/register") public ModelAndView register(Model model) { return
  new ModelAndView(ApplicationConstants.REGISTER, ApplicationConstants.COMMAND,
  new User()); }
  
  @PostMapping("registerUser") public ModelAndView
  addCustomer(@Valid @ModelAttribute("command") User user, BindingResult
  result, ModelMap model) { ModelAndView modelAndView = new ModelAndView(); if
  (result.hasErrors()) {
  
  modelAndView = new ModelAndView(ApplicationConstants.REGISTER,
  ApplicationConstants.COMMAND, user); } else { try {
  registrationService.registerUser(user); Email email = new Email();
  email.setEmailMessage(environment.getProperty(
  "RegistrationController.SUCCESSFUL_REGISTRATION"));
  email.setSubject("Registration confirmation");
  email.setToEmail(user.getEmail());
  //jmsTemplate.convertAndSend("mailbox", email);
  
  modelAndView = new ModelAndView(ApplicationConstants.REGISTER,
  ApplicationConstants.COMMAND, user); modelAndView.addObject("successMessage",
  environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION"));
  
  } catch (UserIdAlreadyPresentException e) {
  
  if (e.getMessage().contains("RegistrationService")) { modelAndView = new
  ModelAndView(ApplicationConstants.REGISTER);
  modelAndView.addObject(ApplicationConstants.COMMAND, user);
  modelAndView.addObject("message", environment.getProperty(e.getMessage()));
  
  }
  
  } } return modelAndView; }
  
  @Bean // Serialize message content to json using TextMessage public
  MessageConverter jacksonJmsMessageConverter() {
  MappingJackson2MessageConverter converter = new
  MappingJackson2MessageConverter(); converter.setTargetType(MessageType.TEXT);
  converter.setTypeIdPropertyName("_type"); return converter; }
  
  }
 