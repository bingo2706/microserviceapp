package com.tanthanh.borrowingservice.command.api.model;

public class Message {
	private String employeeId;
	private String message;
	public Message(String employeeId, String message) {
		super();
		this.employeeId = employeeId;
		this.message = message;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [employeeId=" + employeeId + ", message=" + message + "]";
	}
	
	
}
