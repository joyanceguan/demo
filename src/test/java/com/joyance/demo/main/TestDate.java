package com.joyance.demo.main;

import java.util.Date;

import com.joyance.demo.utils.DateUtils;

public class TestDate {

	public static void main(String[] args) {
		Date date = DateUtils.parseDateFromString("1970-01-01 08:00:00", "yyyy-MM-dd hh:mm:ss");
		String time = DateUtils.parseStringFromDate(date, "yyyy-MM-dd hh:mm:ss");
		System.out.println(time);
	}
}
