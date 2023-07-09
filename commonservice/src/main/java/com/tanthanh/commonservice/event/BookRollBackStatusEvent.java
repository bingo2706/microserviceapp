package com.tanthanh.commonservice.event;

public class BookRollBackStatusEvent {
	private String bookId;
	private Boolean isReady;
	private String employeeId;
	private String borrowId;
	
	
	
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Boolean getIsReady() {
		return isReady;
	}
	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}
}
