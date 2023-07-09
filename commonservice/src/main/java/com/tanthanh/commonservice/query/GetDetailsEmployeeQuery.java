package com.tanthanh.commonservice.query;

public class GetDetailsEmployeeQuery {
	private String employeeId;

	
	
	public GetDetailsEmployeeQuery(String employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
