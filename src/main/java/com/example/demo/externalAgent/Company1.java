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

public class Company1 implements CheckPointRules {

	@Override
	public void validateCheckPoint(Register register, User user) {
		isWeek(register.getDay());
		isFullRegister(register.getRegisterStatus());
		if(register.getRegisterStatus() == RegisterStatus.SECOND) {
			isAfterLunchTime(register.getPoint2());
		}
	}
	
	@Override
	public Register checkPoint(Register register, User user) {
		if(register.getRegisterStatus().equals(RegisterStatus.NONE)) {
			register.setDay(LocalDate.now());
			register.setPoint1(LocalTime.now());
			register.setRegisterStatus(RegisterStatus.FIRST);
			register.setUser(user);
		} else if(register.getRegisterStatus().equals(RegisterStatus.FIRST)) {
			register.setPoint2(LocalTime.now());
			register.setRegisterStatus(RegisterStatus.SECOND);
		} else if(register.getRegisterStatus().equals(RegisterStatus.SECOND)) {
			register.setPoint3(LocalTime.now());
			register.setRegisterStatus(RegisterStatus.THIRD);
		}else {
			register.setPoint4(LocalTime.now());
			register.setRegisterStatus(RegisterStatus.FOURTH);
		} 
		
		return register;
	}
	
	
	private void isWeek(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			throw new ValidationErrorException("You can check today becase is weekend");
		}
	}
	
	private void isAfterLunchTime(LocalTime time) {
		LocalTime now = LocalTime.now();
		if(MINUTES.between(time,now) < 60) {
			throw new ValidationErrorException("You can check now because you are in lunch time");
		}
	}
	
	private void isFullRegister(RegisterStatus registerStatus) {
		if(registerStatus.equals(RegisterStatus.FOURTH)) {
			throw new ValidationErrorException("All points marked");
		}
	}

}
