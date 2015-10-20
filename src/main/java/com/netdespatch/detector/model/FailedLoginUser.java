package com.netdespatch.detector.model;

import java.io.Serializable;
import java.util.Date;

public class FailedLoginUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2340565696487172194L;
	
	private String ipAddress;
	private Date lastLoginAttempt;
	private int failedAttempts;
	private String userName;
	
	public FailedLoginUser(String ipAddress, Date lastLoginAttempt, String userName, int failedAttempts) {
		this.ipAddress = ipAddress;
		this.lastLoginAttempt = lastLoginAttempt;
		this.userName = userName;
		this.failedAttempts = failedAttempts;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public Date getLastLoginAttemptTime() {
		return lastLoginAttempt;
	}
	
	public void setLastLoginAttemptTime(Date firstAttemptTime) {
		this.lastLoginAttempt = firstAttemptTime;
	}
	
	public int getFailedAttempts() {
		return failedAttempts;
	}
	
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + failedAttempts;
		result = prime * result + ((lastLoginAttempt == null) ? 0 : lastLoginAttempt.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FailedLoginUser other = (FailedLoginUser) obj;
		if (failedAttempts != other.failedAttempts)
			return false;
		if (lastLoginAttempt == null) {
			if (other.lastLoginAttempt != null)
				return false;
		} else if (!lastLoginAttempt.equals(other.lastLoginAttempt))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}
