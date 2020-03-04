package com.iFundi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iFundi.handlers.CustomResponse;
import com.iFundi.handlers.UserResponse;
import com.iFundi.models.Transactions;
import com.iFundi.services.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/transactions/save")
	public ResponseEntity<?> addUser(@RequestBody Transactions transactions) throws Exception {
		Transactions transaction = transactionService.saveTransactions(transactions);
		if (transaction == null) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to save transaction"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, true, "transaction records saved"),
				HttpStatus.OK);
	}

}
