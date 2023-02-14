package com.tanthanh.borrowingservice.command.api.aggregate;

import java.util.Date;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.tanthanh.borrowingservice.command.api.command.CreateBorrowCommand;
import com.tanthanh.borrowingservice.command.api.command.DeleteBorrowCommand;
import com.tanthanh.borrowingservice.command.api.command.SendMessageCommand;
import com.tanthanh.borrowingservice.command.api.command.UpdateBookReturnCommand;
import com.tanthanh.borrowingservice.command.api.events.BorrowCreatedEvent;
import com.tanthanh.borrowingservice.command.api.events.BorrowDeletedEvent;
import com.tanthanh.borrowingservice.command.api.events.BorrowSendMessageEvent;
import com.tanthanh.borrowingservice.command.api.events.BorrowingUpdateBookReturnEvent;

@Aggregate
public class BorrowAggregate {
	@AggregateIdentifier
	private String id;
	
	private String bookId;
	private String employeeId;
	private Date borrowingDate;
	private Date returnDate;
	
	private String message;
	public BorrowAggregate() {}
	
	@CommandHandler
	public BorrowAggregate(CreateBorrowCommand command) {
		BorrowCreatedEvent event = new BorrowCreatedEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@EventSourcingHandler
	public void on(BorrowCreatedEvent event) {
		this.bookId = event.getBookId();
		this.borrowingDate = event.getBorrowingDate();
		this.employeeId = event.getEmployeeId();
		this.id = event.getId();
	
	}
	@CommandHandler
	public void handle(DeleteBorrowCommand command) {
		BorrowDeletedEvent event = new BorrowDeletedEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@EventSourcingHandler
	public void on(BorrowDeletedEvent event) {
		this.id = event.getId();
	}
	@CommandHandler
	public void handle(SendMessageCommand command) {
		BorrowSendMessageEvent event = new BorrowSendMessageEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@EventSourcingHandler
	public void on(BorrowSendMessageEvent event) {
		this.id = event.getId();
		this.message = event.getMessage();
		this.employeeId = event.getEmployeeId();
	}
	@CommandHandler
	public void handle(UpdateBookReturnCommand command) {
		BorrowingUpdateBookReturnEvent event = new BorrowingUpdateBookReturnEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@EventSourcingHandler
	public void on(BorrowingUpdateBookReturnEvent event) {
		
		this.returnDate = event.getReturnDate();
		this.bookId = event.getBookId();
		this.employeeId = event.getEmployee();
	}
}
