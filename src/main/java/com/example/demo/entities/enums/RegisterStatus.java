package com.example.demo.entities.enums;

public enum RegisterStatus {
	NONE(0), FIRST(1), SECOND(2), THIRD(3), FOURTH(4);

	private int code;

	private RegisterStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RegisterStatus valueOf(int code) {
		for (RegisterStatus value : RegisterStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid RegisterStatus code");
	}
}
