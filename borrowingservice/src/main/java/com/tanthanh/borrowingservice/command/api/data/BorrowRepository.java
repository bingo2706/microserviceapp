package com.tanthanh.borrowingservice.command.api.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrowing, String>{
	List<Borrowing> findByEmployeeIdAndReturnDateIsNull(String employeeId);
	Borrowing findByEmployeeIdAndBookIdAndReturnDateIsNull(String employeeId,String bookId);
}