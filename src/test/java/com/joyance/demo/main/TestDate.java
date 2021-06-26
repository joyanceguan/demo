package com.joyance.demo.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.joyance.demo.utils.DateUtils;

public class TestDate {

	public static void main(String[] args) {
		Date date = DateUtils.parseDateFromString("1970-01-01 08:00:00", "yyyy-MM-dd hh:mm:ss");
		String time = DateUtils.parseStringFromDate(date, "yyyy-MM-dd hh:mm:ss");
		System.out.println(time);
		
		Map<Long,String> map = new HashMap<Long,String>();
		map.put(1731014656l, "abc");
		
		String x = map.get(new Long(1731014656));
		System.out.println("======="+x);
	}
}
