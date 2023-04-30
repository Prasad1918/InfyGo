package com.infosys.irs.model;

public class Email {
	private String toEmail;
	private String subject;
	private String emailMessage;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	@Override
	public String toString() {
		return String.format("Email{To=%s, Subject=%s, Message=%s}", getToEmail(), getSubject(), getEmailMessage());
	}
}
