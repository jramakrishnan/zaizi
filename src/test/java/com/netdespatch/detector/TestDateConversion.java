package com.netdespatch.detector;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.Date;

import org.junit.Test;

import com.netdespatch.detector.util.DateUtil;

public class TestDateConversion {
	@Test
	public void whenEpochTimeIsUsedThenReturnDateObject(){
		Date output = DateUtil.epochToDate("1336129800");
		assertThat(output, instanceOf(Date.class));
	}
	
	@Test
	public void whenTimeDifferenceIsMoreThanOrEqualsToFiveMinutesThenReturnTrue(){
		assertEquals(DateUtil.isTimeDifferenceEqualsOrMoreThan5Minutes(
				DateUtil.epochToDate("1336129380"), DateUtil.epochToDate("1336129680")), true);
	}
	
	@Test
	public void whenTimeDifferenceIsLessThanFiveMinutesThenReturnFalse(){
		assertEquals(DateUtil.isTimeDifferenceEqualsOrMoreThan5Minutes(
				DateUtil.epochToDate("1336129440"), DateUtil.epochToDate("1336129680")), false);
	}
	
	@Test
	public void whenNullObjectsPassedThenReturn0(){
		assertEquals(DateUtil.isTimeDifferenceEqualsOrMoreThan5Minutes(
				null, null), false);
	}
}
