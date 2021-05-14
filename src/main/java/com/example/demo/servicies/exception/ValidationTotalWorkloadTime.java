package com.example.demo.servicies.exception;

import java.time.LocalDate;

public class ValidationTotalWorkloadTime {

	public static void validate(Long month, Long year) {
		if (month <= 0 || month > 12) {
			throw new NullPointException("Month");
		}
		if (year < 0 || year > LocalDate.now().getYear()) {
			throw new NullPointException("Year");
		}
	}
}
