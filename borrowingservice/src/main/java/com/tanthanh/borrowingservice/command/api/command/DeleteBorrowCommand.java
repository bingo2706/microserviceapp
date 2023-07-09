package com.tanthanh.borrowingservice.command.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteBorrowCommand {
	@TargetAggregateIdentifier
	private String id;

	
	
	public DeleteBorrowCommand(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
