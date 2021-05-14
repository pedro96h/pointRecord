package com.example.demo.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	public UserDto() {
	}

	public UserDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
