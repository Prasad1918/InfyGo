package com.infosys.irs.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.infosys.irs.model.Email;

@Component
public class EmailListener {

	private final Logger logger = LoggerFactory.getLogger(EmailListener.class);
	
	@JmsListener(destination = "mailbox")
	public void listenAndMail(Email email) {
		
		String logMsg="Received <" + email + ">";
		
		logger.info(logMsg);
		// msg contains customer's name, password and e-mail id

		// Write code to trigger e-mail to the captured id
	}

}
