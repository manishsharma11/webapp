package com.main.sts.service;

public class SMSInfo {

	// String authKey;
	String mobile;
	// String senderId;
	String message;
	String route;
	// should Send messages are enabled?
	boolean sendEnabled = false;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public boolean isSendEnabled() {
		return sendEnabled;
	}

	public void setSendEnabled(boolean enabled) {
		this.sendEnabled = enabled;
	}

}
