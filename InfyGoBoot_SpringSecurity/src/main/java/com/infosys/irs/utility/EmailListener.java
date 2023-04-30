package com.infosys.irs.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.infosys.irs.model.Email;

@Component
public class EmailListener {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@JmsListener(destination = "mailbox")
	public void listenAndMail(Email email) {
		String data =email.toString();
		logger.info("Received <");
		logger.info(data);
		logger.info(">");
	}

}
