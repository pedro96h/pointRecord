package com.example.demo.externalAgent;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.entities.Register;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.RegisterStatus;
import com.example.demo.interfaces.CheckPointRules;
import com.example.demo.servicies.exception.ValidationErrorException;

public class Company1 extends CompanyMain implements CheckPointRules {

	public Company1() {
		super(8, 4);
	}

	@Override
	public void validateCheckPoint(Register register, User user) {
		isWeek(register.getDay());
		isFullRegister(register.getRegisterStatus());
		if (register.getRegisterStatus() == RegisterStatus.SECOND) {
			isAfterLunchTime(register.getPoint2());
		}
	}

	@Override
	public Register checkPoint(Register register, User user) {
		Register.addRegister(register, user);
		calculateTimeRecord(register);
		return register;
	}

	private void isWeek(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			throw new ValidationErrorException("You can check today becase is weekend");
		}
	}

	private void isAfterLunchTime(LocalTime time) {
		LocalTime now = LocalTime.now();
		if (MINUTES.between(time, now) < 60) {
			throw new ValidationErrorException("You can check now because you are in lunch time");
		}
	}

	private void isFullRegister(RegisterStatus registerStatus) {
		if (registerStatus.equals(RegisterStatus.FOURTH)) {
			throw new ValidationErrorException("All points marked");
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void calculateTimeRecord(Register register) {
		Long totalPerDayMins = this.getWorkload() * -60L;
		Long response = 0L;

		switch (register.getRegisterStatus()) {
		case FOURTH: {
			response += MINUTES.between(register.getPoint3(), register.getPoint4());
		}
		case SECOND: {
			response += MINUTES.between(register.getPoint1(), register.getPoint2());
		}
		}

		response += totalPerDayMins;
		register.setTimeRecord(response);
	}

}
