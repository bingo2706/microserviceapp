package com.tanthanh.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.tanthanh.bookservice.command.command.CreateBookCommand;
import com.tanthanh.bookservice.command.command.DeleteBookCommand;
import com.tanthanh.bookservice.command.command.UpdateBookCommand;
import com.tanthanh.bookservice.command.event.BookCreatedEvent;
import com.tanthanh.bookservice.command.event.BookDeletedEvent;
import com.tanthanh.bookservice.command.event.BookUpdatedEvent;
import com.tanthanh.commonservice.command.RollBackStatusBookCommand;
import com.tanthanh.commonservice.command.UpdateStatusBookCommand;
import com.tanthanh.commonservice.event.BookRollBackStatusEvent;
import com.tanthanh.commonservice.event.BookUpdateStatusEvent;

@Aggregate
public class BookAggregate {
	
	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;
	
	 public BookAggregate() {
		 
	    }
	 @CommandHandler
	    public BookAggregate(CreateBookCommand createBookCommand) {
	        
	        BookCreatedEvent bookCreatedEvent
	                = new BookCreatedEvent();
	        BeanUtils.copyProperties(createBookCommand,bookCreatedEvent);
	        AggregateLifecycle.apply(bookCreatedEvent);
	    }
	 @CommandHandler
	    public void handle(UpdateBookCommand updateBookCommand) {
	        
	        BookUpdatedEvent bookUpdatedEvent
	                = new BookUpdatedEvent();
	        BeanUtils.copyProperties(updateBookCommand,bookUpdatedEvent);
	        AggregateLifecycle.apply(bookUpdatedEvent);
	    }
	 @CommandHandler
	    public void handle(DeleteBookCommand deleteBookCommand) {
	        
	        BookDeletedEvent deletedEvent
	                = new BookDeletedEvent();
	        BeanUtils.copyProperties(deleteBookCommand,deletedEvent);
	        AggregateLifecycle.apply(deletedEvent);
	    }
	 @CommandHandler
	 public void handle(UpdateStatusBookCommand command) {
		 BookUpdateStatusEvent event = new BookUpdateStatusEvent();
		 BeanUtils.copyProperties(command, event);
		 AggregateLifecycle.apply(event);
	 }
	 @EventSourcingHandler
		public void on(BookUpdateStatusEvent event) {
			this.bookId = event.getBookId();
			this.isReady = event.getIsReady();
		}
	 @EventSourcingHandler
	    public void on(BookCreatedEvent event) {
			this.bookId = event.getBookId();
			this.author = event.getAuthor();
			this.isReady = event.getIsReady();
			this.name = event.getName();
	    }
	 @EventSourcingHandler
	    public void on(BookUpdatedEvent event) {
			this.bookId = event.getBookId();
			this.author = event.getAuthor();
			this.isReady = event.getIsReady();
			this.name = event.getName();
	    }
	 @EventSourcingHandler
	    public void on(BookDeletedEvent event) {
			this.bookId = event.getBookId();
			
	    }
	 @CommandHandler
	 public void handle(RollBackStatusBookCommand command) {
		 BookRollBackStatusEvent event = new BookRollBackStatusEvent();
		 BeanUtils.copyProperties(command, event);
		 AggregateLifecycle.apply(event);
	 }
	 @EventSourcingHandler
		public void on(BookRollBackStatusEvent event) {
			this.bookId = event.getBookId();
			this.isReady = event.getIsReady();
		}
}
