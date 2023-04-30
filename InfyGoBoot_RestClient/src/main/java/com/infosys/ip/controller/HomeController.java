package com.infosys.ip.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.ip.model.SearchFlights;

@Controller
@SessionAttributes("contextPath")
@PropertySource("classpath:configuration.properties")
public class HomeController {

	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;

	public HomeController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();

	}

	@GetMapping(value = "/flight")
	public ModelAndView getSourceDestinationDetails(HttpSession session, HttpServletRequest request,
			SessionStatus status) {
		String restUrl = environment.getProperty("RestUrl");
		session.setAttribute("contextPath", request.getContextPath());
		status.setComplete();
		List<String> sources = restTemplate.getForObject(restUrl + "/FlightAPI/source", List.class);
		List<String> destinations = restTemplate.getForObject(restUrl + "/FlightAPI/destination", List.class);
		ModelAndView modelAndView = new ModelAndView("searchFlights", "command", new SearchFlights());
		modelAndView.addObject("sourceList", sources);
		modelAndView.addObject("destinationList", destinations);
		return modelAndView;
	}
}
