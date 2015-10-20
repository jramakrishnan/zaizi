package com.netdespatch.detector;

import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.netdespatch.detector.impl.HackerDetectorImpl;

public class HackerDetectorImplTest {
	
	HackerDetector detector = new HackerDetectorImpl();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenEmptyStringIsUsedAsAnInputThenThrowNullPointerException(){
    	exception.expect(NullPointerException.class);
    	detector.parseLine("");
    }
    
    @Test
    public void whenNullIsUsedAsAnInputThenThrowNullPointerException(){
    	exception.expect(NullPointerException.class);
    	detector.parseLine(null);    	
    }
    
    @Test
    public void whenSuccessfulLoginAttemptLogPassedThenReturnNull(){
    	final String successLog = "80.238.9.179,133612947,SIGNIN_SUCCESS,Bruce.Banner";
    	assertEquals(detector.parseLine(successLog), null);
    }
    
    
    @Test
    public void whenInvalidLoginAttemptLogPassedThenReturnNull(){
    	final String invalidAttempt1 = "80.238.9.180,1336129440,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt1), null);
    }
    
    @Test 
    public void whenSuccessiveInvalidLoginAttemptLogPassedInTheIntervalOfThirtySecondsThenReturnIPAddress(){
    	
    	final String invalidAttempt1 = "80.238.9.179,1336129440,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt1), null);
    	final String invalidAttempt2 = "80.238.9.179,1336129470,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt2), null);
    	final String invalidAttempt3 = "80.238.9.179,1336129500,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt3), null);
    	final String invalidAttempt4 = "80.238.9.179,1336129530,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt4), null);
    	final String invalidAttempt5 = "80.238.9.179,1336129560,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt5), "80.238.9.179");
    }
    
    @Test
    public void whenSuccessiveInvalidLoginLogPassedInTheIntervalOfOneMinuteThenReturnIPAddress(){
       	final String invalidAttempt1 = "80.238.9.181,1336129380,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt1), null);
    	
    	final String invalidAttempt2 = "80.238.9.181,1336129500,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt2), null);
    	
    	final String invalidAttempt3 = "80.238.9.181,1336129560,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt3), null);
    	
    	final String invalidAttempt4 = "80.238.9.181,1336129620,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt4), null);
 	
    	final String invalidAttempt5 = "80.238.9.181,1336129680,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt5), "80.238.9.181");
    }
    
    @Test
    public void whenSuccessiveInvalidLoginLogPassedInTheIntervalOfTwoMinuteThenReturnNull(){
    	
       	final String invalidAttempt1 = "80.238.9.182,1336129440,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt1), null);
    	
    	final String invalidAttempt2 = "80.238.9.182,1336129560,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt2), null);
    	
    	final String invalidAttempt3 = "80.238.9.182,1336129680,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt3), null);
    	
    	final String invalidAttempt4 = "80.238.9.182,1336129800,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt4), null);
    	
    	final String invalidAttempt5 = "80.238.9.182,1336129920,SIGNIN_FAILURE,Bruce.Banner";
    	assertEquals(detector.parseLine(invalidAttempt5), null);
    }
	
}
