package com.tanthanh.borrowingservice.command.api.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrowing")
public class Borrowing {
	@Id
	private String id;
	
	private String bookId;

	private String employeeId;
	private Date borrowingDate;
	private Date returnDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getBorrowingDate() {
		return borrowingDate;
	}
	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
