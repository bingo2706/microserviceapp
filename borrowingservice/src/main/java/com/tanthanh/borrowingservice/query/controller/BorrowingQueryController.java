package com.tanthanh.borrowingservice.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanthanh.borrowingservice.query.model.BorrowingResponseModel;
import com.tanthanh.borrowingservice.query.queries.GetAllBorrowing;
import com.tanthanh.borrowingservice.query.queries.GetListBorrowingByEmployeeQuery;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingQueryController {
	@Autowired
	private QueryGateway queryGateway;
	
	@GetMapping("/{employeeId}")
	public List<BorrowingResponseModel> getBorrowingByEmployee(@PathVariable String employeeId){
		GetListBorrowingByEmployeeQuery getBorrowingQuery = new GetListBorrowingByEmployeeQuery();
		getBorrowingQuery.setEmployeeId(employeeId);
		
		List<BorrowingResponseModel> list = 
			queryGateway.query(getBorrowingQuery, ResponseTypes.multipleInstancesOf(BorrowingResponseModel.class))
			.join();
		
		return list;
	}
	@GetMapping
	public List<BorrowingResponseModel> getAllBorrowing(){
		List<BorrowingResponseModel> list = queryGateway.query(new GetAllBorrowing(), ResponseTypes.multipleInstancesOf(BorrowingResponseModel.class))
				.join();
		return list;
	}
}
