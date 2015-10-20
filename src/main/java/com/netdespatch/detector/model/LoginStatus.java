package com.netdespatch.detector.model;

public enum LoginStatus {
	
	SIGNIN_SUCCESS("SIGNIN_SUCCESS"), SIGNIN_FAILURE("SIGNIN_FAILURE") ;
	
	private String loginStatus;

	private LoginStatus(String s) {
		loginStatus = s;
	}

	public String getStatusCode() {
		return loginStatus;
	}
}
