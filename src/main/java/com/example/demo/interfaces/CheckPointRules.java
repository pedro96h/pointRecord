package com.example.demo.interfaces;

import com.example.demo.entities.Register;
import com.example.demo.entities.User;

public interface CheckPointRules {

	void validateCheckPoint(Register register, User user);

	Register checkPoint(Register register, User user);

}
