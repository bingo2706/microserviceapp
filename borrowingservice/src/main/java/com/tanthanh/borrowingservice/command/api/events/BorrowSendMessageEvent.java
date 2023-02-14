package com.tanthanh.borrowingservice.command.api.events;

public class BorrowSendMessageEvent {
private String id;
	
	private String employeeId;
	private String message;
	public BorrowSendMessageEvent(String id, String employeeId, String message) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.message = message;
	}
	public BorrowSendMessageEvent() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
