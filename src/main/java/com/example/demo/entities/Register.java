package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.entities.enums.RegisterStatus;

@Entity
public class Register implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	private LocalDate day;
	private LocalTime point1;
	private LocalTime point2;
	private LocalTime point3;
	private LocalTime point4;
	private RegisterStatus registerStatus;
	
	public Register() {
		this.day = LocalDate.now();
		this.registerStatus = RegisterStatus.NONE;
	}
	
	public Register(Long id, User user, LocalDate day, LocalTime point1, LocalTime point2, LocalTime point3,
			LocalTime point4,RegisterStatus registerStatus) {
		super();
		this.id = id;
		this.user = user;
		this.day = day;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.point4 = point4;
		this.registerStatus = registerStatus;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalDate getDay() {
		return day;
	}
	
	public void setDay(LocalDate day) {
		this.day = day;
	}
	
	public LocalTime getPoint1() {
		return point1;
	}
	
	public void setPoint1(LocalTime point1) {
		this.point1 = point1;
	}
	
	public LocalTime getPoint2() {
		return point2;
	}
	
	public void setPoint2(LocalTime point2) {
		this.point2 = point2;
	}
	
	public LocalTime getPoint3() {
		return point3;
	}
	
	public void setPoint3(LocalTime point3) {
		this.point3 = point3;
	}
	
	public LocalTime getPoint4() {
		return point4;
	}
	
	public void setPoint4(LocalTime point4) {
		this.point4 = point4;
	}
	
	public RegisterStatus getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(RegisterStatus registerStatus) {
		this.registerStatus = registerStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
