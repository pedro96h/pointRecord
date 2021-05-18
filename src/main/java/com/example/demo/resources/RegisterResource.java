package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Register;
import com.example.demo.servicies.RegisterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/register")
@Api(value = "API REST register")
@CrossOrigin(origins = "*")
public class RegisterResource {

	@Autowired
	private RegisterService registerService;

	@GetMapping
	@ApiOperation(value = "returns all records from the database")
	public ResponseEntity<List<Register>> findAll() {
		List<Register> list = registerService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "returns a record from the database by an id")
	public ResponseEntity<Register> findById(@PathVariable Long id) {
		Register register = registerService.findById(id);
		return ResponseEntity.ok().body(register);
	}

	@PostMapping(value = "/checkpoint/{userId}")
	@ApiOperation(value = "Register a point")
	public ResponseEntity<Register> checkPoint(@PathVariable Long userId) {
		Register register = registerService.checkPoint(userId);
		return ResponseEntity.ok().body(register);
	}

	@GetMapping(value = "getTotalWorkloadTime/{month}/{year}/{userId}")
	@ApiOperation(value = "returns total hours (months) of a user")
	public ResponseEntity<String> getTotalWorkloadTime(@PathVariable Long month , @PathVariable Long year,
			@PathVariable Long userId) {
		String totalTime = registerService.getTotalWorkloadTime(month,year,userId);
		return ResponseEntity.ok().body(totalTime);
	}

}
