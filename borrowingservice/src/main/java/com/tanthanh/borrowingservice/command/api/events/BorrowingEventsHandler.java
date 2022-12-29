package com.tanthanh.borrowingservice.command.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanthanh.borrowingservice.command.api.data.BorrowRepository;
import com.tanthanh.borrowingservice.command.api.data.Borrowing;

@Component
public class BorrowingEventsHandler {
	@Autowired
	private BorrowRepository borrowRepository;
	
	@EventHandler
	public void on(BorrowCreatedEvent event) {
		Borrowing model = new Borrowing();
		
		BeanUtils.copyProperties(event, model);
		
		borrowRepository.save(model);
	}
}
