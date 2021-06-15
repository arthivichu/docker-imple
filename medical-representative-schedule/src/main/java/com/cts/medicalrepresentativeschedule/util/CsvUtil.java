package com.cts.medicalrepresentativeschedule.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cts.medicalrepresentativeschedule.model.Doctor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvUtil {

	public static List<Doctor> getDoctors() {
		log.info("Start");
		List<Doctor> doctors = new ArrayList<>();

		ClassLoader loader = CsvUtil.class.getClassLoader();
		InputStream in = loader.getResourceAsStream("Doctor.csv");

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String input = null;

			input = reader.readLine();

			while (input != null) {
				String[] row = input.split(",");

				Doctor doctor = new Doctor(Integer.parseInt(row[0]), row[1], row[2], row[3]);

				doctors.add(doctor);
				input = reader.readLine();
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		}

		log.debug("Doctors : {}", doctors);
		log.info("End");
		return doctors;
	}

}
