package com.example.demo.servicies;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.servicies.exception.DatabaseConstraintViolationException;
import com.example.demo.servicies.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(()-> new ResourceNotFoundException(id));		
	}

	public User addUser(User user) {
		try {
			return userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseConstraintViolationException(e.getMessage());
		}
	}

	public void delete(Long id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public User update(Long id, User user) {
		try {
			User entity = userRepository.getOne(id);
			updateData(entity,user);
			return userRepository.save(entity);	
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setCpf(obj.getCpf());
	}
	
}
