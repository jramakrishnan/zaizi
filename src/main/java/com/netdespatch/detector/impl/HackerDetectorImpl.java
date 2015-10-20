package com.netdespatch.detector.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.netdespatch.detector.HackerDetector;
import com.netdespatch.detector.model.FailedLoginUser;
import com.netdespatch.detector.model.LoginStatus;
import com.netdespatch.detector.util.DateUtil;

/**
 * 
 * This class is used to detect the repetitive invalid login attempt within five minutes time frame.
 * If the user log contains attempts to invalid login more than five times in five minutes then the
 * user IP Address will returned.
 * 
 * @author jay
 *
 */
public class HackerDetectorImpl implements HackerDetector {
	
	private static Map<String,FailedLoginUser> loginAttempts =
		    new ConcurrentHashMap<String,FailedLoginUser>(1000);
	
	private final int INCREMENT = 1;
	private final int MAX_ATTEMPT = 5;
	
	/**
	 * This method used to parse the log string which is in the format of
	 * "80.238.9.179,133612947,SIGNIN_SUCCESS,Bruce.Banner".
	 * 
	 * @param line This is a single line of the generated log
	 * @return String IP Address of the successive login (invalid) attempts with in 5 minutes
	 *         or else null will be returned. 
	 */
	public String parseLine(final String line) {
		
		if(line == null || line.equals("")){
			throw new NullPointerException();
		}
		
		//Track failure attempts
		if(line.contains(LoginStatus.SIGNIN_FAILURE.getStatusCode())){
			return findHackAttempt(line);
		}
		
		// if the log doesn't contains login failure then return null
		return null;
	}

	/**
	 * This method is used to parse and track the invalid successive (5) login attempts in 
	 * "5 minutes - time frame". The user IP address will be returned in the event of 
	 * repeated login attempt or else null will be returned
	 * @param line This is a single line of the generated log
	 * @return IP Address
	 */
	private String findHackAttempt(String line) {
		
		String[] logFragments = line.split(",");		
		
		FailedLoginUser user = loginAttempts.get(logFragments[0]);
		
		if(user == null){			
			// create a new new invalid login attempt
			user = new FailedLoginUser(logFragments[0], 
					DateUtil.epochToDate(logFragments[1]), logFragments[2], INCREMENT);
			loginAttempts.put(logFragments[0],user);		
		} else {			
			Date accessTime = DateUtil.epochToDate(logFragments[1]);	
			
			int count = user.getFailedAttempts() + INCREMENT;
			boolean isTimeDifferenceEqualsOrMoreThan5Minutes = DateUtil.isTimeDifferenceEqualsOrMoreThan5Minutes(
					user.getLastLoginAttemptTime(), accessTime);
			
			if(isTimeDifferenceEqualsOrMoreThan5Minutes){
				if(count >= MAX_ATTEMPT){
					// Remove the user data in temporary memory cache and return the potential threat IP Address
					loginAttempts.remove(user.getIpAddress());
					return user.getIpAddress();
				} else{
					// Reset the time to the most recent hack attempt
					user.setLastLoginAttemptTime(accessTime);
					// Reset hack attempt count
					user.setFailedAttempts(INCREMENT);
					loginAttempts.put(user.getIpAddress(), user);	
				}
			} else if(!isTimeDifferenceEqualsOrMoreThan5Minutes && count >= MAX_ATTEMPT){
				// Remove the user data in temporary memory cache and return the potential threat IP Address
				loginAttempts.remove(user.getIpAddress());
				return user.getIpAddress();
			} else {
				user.setFailedAttempts(count);
				loginAttempts.put(user.getIpAddress(), user);
			}
		}		
		
		return null;
	}			
}
