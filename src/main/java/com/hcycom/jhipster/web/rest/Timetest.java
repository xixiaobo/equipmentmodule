package com.hcycom.jhipster.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hcycom.jhipster.web.rest.tools.TimeUtil;

public class Timetest {
	public static void main(String[] args) {
		Date newTime = new Date();
		String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newTime);
		
		String oldDate=TimeUtil.getTimeByMinute(-5);
		System.out.println(newDate);
		System.out.println(oldDate);
		
	}
}
