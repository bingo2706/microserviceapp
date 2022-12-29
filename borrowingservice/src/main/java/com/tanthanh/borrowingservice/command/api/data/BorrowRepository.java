package com.tanthanh.borrowingservice.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrowing, String>{
	
}