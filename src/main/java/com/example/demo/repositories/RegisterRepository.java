package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Register;
import com.example.demo.entities.User;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

	Optional<Register> findByUserAndDay(User user, LocalDate date);
}
