package com.infosys.irs.utility;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.infosys.irs.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class EmailListener {
	final Logger logger= LoggerFactory.getLogger(this.getClass());
	@JmsListener(destination = "mailbox")
	 public void listenAndMail(Email email) 
	 {

		String data = String.format("Received :", email);
		logger.info("Received <");
		logger.info(data);
		logger.info(">");
		logger.info(data, email);
	        //msg contains customer's name, password and e-mail id
	        
	        //Write code to trigger e-mail to the captured id
	 }

}
