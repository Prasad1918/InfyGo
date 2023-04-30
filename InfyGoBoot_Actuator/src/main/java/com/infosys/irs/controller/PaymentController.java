/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.irs.exception.InvalidCardDetailsException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.CreditCard;
import com.infosys.irs.model.User;
import com.infosys.irs.service.PaymentService;
import com.infosys.irs.service.UserService;
import com.infosys.irs.utility.ApplicationConstants;

@Controller
@SessionAttributes("booking")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;

	@PostMapping("/makePayment")
	public ModelAndView processPayment(@Valid @ModelAttribute("command") CreditCard creditCard,
			BindingResult bindingResult, ModelMap model, @RequestParam("fare") String fare, HttpSession session, SessionStatus status) {

		ModelAndView modelAndView = new ModelAndView("paymentSuccess", ApplicationConstants.COMMAND, creditCard);
		if (bindingResult.hasErrors()) {
			modelAndView = new ModelAndView("payment", ApplicationConstants.COMMAND, creditCard);
		} else {

			try {
				paymentService.findCreditCard(creditCard);
				status.setComplete();
				paymentService.updateCreditCard(creditCard.getCardNumber(), fare);

				paymentService.confirmBooking(session);
				modelAndView.addObject("paymentMessage", environment.getProperty("PaymentController.PAYMENT_SUCCESS"));
				modelAndView.addObject("pnrMessage", environment.getProperty("PaymentController.PNR_DETAIL"));
			} catch (InvalidCardDetailsException e) {
				if (e.getMessage().contains("PaymentService")) {
					modelAndView = new ModelAndView("payment", ApplicationConstants.COMMAND, creditCard);

				}

				modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			} catch (Exception e) {
				throw e;
			}

		}

		return modelAndView;

	}

	@GetMapping("/downloadTicket.pdf")
	public void downloadTicket(Model model, HttpSession session) {
		User user = null;

		String pnr = ((Booking) session.getAttribute(ApplicationConstants.BOOKING)).getPnr().toString();
		String seats = ((Booking) session.getAttribute(ApplicationConstants.BOOKING)).getSeats().toString();
		model.addAttribute("pnr", pnr);
		model.addAttribute("noOfSeats", seats);
		String userId = ((Booking) session.getAttribute(ApplicationConstants.BOOKING)).getName();
		user = userService.getUserDetails(userId);

		model.addAttribute("user", user.getName());

	}

}
