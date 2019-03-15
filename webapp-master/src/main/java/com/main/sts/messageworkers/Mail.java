package com.main.sts.messageworkers;

public class Mail {

	private String sendTo;
	private String message;
	private String subject;

	public Mail(String sendTo, String subject, String message) {

		this.message = message;
		this.sendTo = sendTo;
		this.subject = subject;
	}

	public String getSendTo() {
		return sendTo;
	}

	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}

}
