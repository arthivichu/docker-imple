package com.cts.medicalrepresentativeschedule.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDate getLocalDate(String date) {

		LocalDate localDate = null;

		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			localDate = LocalDate.parse(date, format);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return localDate;
	}

}
