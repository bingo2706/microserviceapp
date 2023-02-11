package com.tanthanh.borrowingservice.command.api.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.tanthanh.borrowingservice.command.api.command.DeleteBorrowCommand;
import com.tanthanh.borrowingservice.command.api.events.BorrowCreatedEvent;
import com.tanthanh.commonservice.command.UpdateStatusBookCommand;
import com.tanthanh.commonservice.model.BookResponseCommonModel;
import com.tanthanh.commonservice.query.GetDetailsBookQuery;

@Saga
public class BorrowingSaga {
	@Autowired 
	private transient CommandGateway commandGateway;
	 
	 @Autowired 
	 private transient QueryGateway queryGateway;
	 
	 @StartSaga
	 @SagaEventHandler(associationProperty = "id")
	 private void handle(BorrowCreatedEvent event) {
		 System.out.println("BorrowCreatedEvent in Saga for BookId : "+event.getBookId()+ " : EmployeeId :  "+event.getEmployeeId());
	    	
    	 try {
    		 SagaLifecycle.associateWith("bookId", event.getBookId());
    		 
    		 GetDetailsBookQuery getDetailsBookQuery = new GetDetailsBookQuery(event.getBookId());
    		
    		 BookResponseCommonModel bookResponseModel =
    			        queryGateway.query(getDetailsBookQuery,
    			                ResponseTypes.instanceOf(BookResponseCommonModel.class))
    			                .join();
    		 if(bookResponseModel.getIsReady() == true) {
    			 UpdateStatusBookCommand command = new UpdateStatusBookCommand(event.getBookId(), false,event.getEmployeeId(),event.getId());
    			 commandGateway.sendAndWait(command);
    		 }
    		 else {
    			 
    			 throw new Exception("Sach Da co nguoi Muon");
    		 }
    		 
    		
		} catch (Exception e) {
			rollBackBorrowRecord(event.getId());
			
			System.out.println(e.getMessage());
		}
	 }
	  private void rollBackBorrowRecord(String id) {
	    	commandGateway.sendAndWait(new DeleteBorrowCommand(id));
	    }
}
