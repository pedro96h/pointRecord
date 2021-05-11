package com.example.demo.externalAgent;

public abstract class CompanyMain {

	private Integer workload;
	private Integer numberOfRecords;

	public CompanyMain() {
	}

	public CompanyMain(Integer workload, Integer numberOfRecords) {
		this.workload = workload;
		this.numberOfRecords = numberOfRecords;
	}

	public Integer getWorkload() {
		return workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

}
