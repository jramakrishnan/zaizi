package com.netdespatch.detector;

import static org.junit.Assert.*;

import org.junit.Test;

import com.netdespatch.detector.model.LoginStatus;

public class TestLoginStatus {
	@Test
	public void assertValidLoginStatus(){
		assertEquals(LoginStatus.SIGNIN_SUCCESS.getStatusCode(), "SIGNIN_SUCCESS");
	}
	
	@Test
	public void assertInValidLoginStatus(){
		assertEquals(LoginStatus.SIGNIN_FAILURE.getStatusCode(), "SIGNIN_FAILURE");
	}
	
	
}
