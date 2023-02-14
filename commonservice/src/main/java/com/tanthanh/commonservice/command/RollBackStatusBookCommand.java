package com.tanthanh.commonservice.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackStatusBookCommand {
	@TargetAggregateIdentifier
	private String bookId;
	private Boolean isReady;
	private String employeeId;
	private String borrowId;
	
	public RollBackStatusBookCommand(String bookId, Boolean isReady, String employeeId, String borrowId) {
		super();
		this.bookId = bookId;
		this.isReady = isReady;
		this.employeeId = employeeId;
		this.borrowId = borrowId;
	}
	
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
