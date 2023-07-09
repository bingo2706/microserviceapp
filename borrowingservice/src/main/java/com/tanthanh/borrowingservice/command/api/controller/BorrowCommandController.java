package com.tanthanh.borrowingservice.command.api.controller;

import java.util.Date;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanthanh.borrowingservice.command.api.command.CreateBorrowCommand;
import com.tanthanh.borrowingservice.command.api.command.UpdateBookReturnCommand;
import com.tanthanh.borrowingservice.command.api.model.BorrowRequestModel;
import com.tanthanh.borrowingservice.command.api.service.BorrowService;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowCommandController {
	@Autowired
	private CommandGateway commandGateway;
	
	@Autowired
	private BorrowService borrowService;
	
	@PostMapping
	public String addBookBorrowing(@RequestBody BorrowRequestModel model) {
		try {
			CreateBorrowCommand command = 
					new CreateBorrowCommand(model.getBookId(), model.getEmployeeId(), new Date(),UUID.randomUUID().toString());
				commandGateway.sendAndWait(command);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "Book borrowing added";
	}
	@PutMapping
	public String updateBookReturn(@RequestBody BorrowRequestModel model) {
		UpdateBookReturnCommand command = new UpdateBookReturnCommand(borrowService.findIdBorrowing(model.getEmployeeId(), model.getBookId()), model.getBookId(),model.getEmployeeId(),new Date());
		commandGateway.sendAndWait(command);
		return "Book returned";
	}
}
