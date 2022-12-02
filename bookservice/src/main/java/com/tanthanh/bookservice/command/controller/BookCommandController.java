package com.tanthanh.bookservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanthanh.bookservice.command.command.CreateBookCommand;
import com.tanthanh.bookservice.command.command.DeleteBookCommand;
import com.tanthanh.bookservice.command.command.UpdateBookCommand;
import com.tanthanh.bookservice.command.model.BookRequestModel;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping
	public String addBook(@RequestBody BookRequestModel model) {
		CreateBookCommand command = 
				new CreateBookCommand(UUID.randomUUID().toString(),model.getName(), model.getAuthor(), true);
		commandGateway.sendAndWait(command);
		return "added Book";
	}
	@PutMapping
	public String updateBook(@RequestBody BookRequestModel model) {
		UpdateBookCommand command = 
				new UpdateBookCommand(model.getBookId(),model.getName(), model.getAuthor(), model.getIsReady());
		commandGateway.sendAndWait(command);
		return "updated Book";
	}
	@DeleteMapping("/{bookId}")
	public String deleteBook(@PathVariable String bookId) {
		
		DeleteBookCommand command = 
				new DeleteBookCommand(bookId);
		commandGateway.sendAndWait(command);
		return "deleted Book";
	}
}
