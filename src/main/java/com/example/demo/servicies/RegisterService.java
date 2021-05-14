package com.example.demo.servicies;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Register;
import com.example.demo.entities.User;
import com.example.demo.externalAgent.Company1;
import com.example.demo.interfaces.CheckPointRules;
import com.example.demo.repositories.RegisterRepository;
import com.example.demo.servicies.exception.ResourceNotFoundException;
import com.example.demo.servicies.exception.ValidationTotalWorkloadTime;

@Service
public class RegisterService {

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	UserService userService;

	public List<Register> findAll() {
		return registerRepository.findAll();
	}

	public Register findById(Long id) {
		Optional<Register> register = registerRepository.findById(id);
		return register.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Register checkPoint(Long id) {
		User user = userService.findById(id);
		Optional<Register> register = registerRepository.findByUserAndDay(user, LocalDate.now());
		return pointRecord(register, user);
	}

	private Register pointRecord(Optional<Register> optRegister, User user) {
		Register register = optRegister.orElse(new Register());
		CheckPointRules checkPointRules = new Company1();
		checkPointRules.validateCheckPoint(register, user);
		return registerRepository.save(checkPointRules.checkPoint(register, user));
	}

	public String getTotalWorkloadTime(Long month, Long year, Long userId) {
		ValidationTotalWorkloadTime.validate(month, year);
		User user = userService.findById(userId);
		Long totalTime = registerRepository.getTotalWorkloadTime(month.intValue(), year.intValue(), user);

		Long hh = totalTime / 60;
		Long mm = totalTime % 60;

		String response = "hours balance : " + hh.toString() + ":" + Integer.toString((int) Math.abs(mm));
		return response;
	}
}
