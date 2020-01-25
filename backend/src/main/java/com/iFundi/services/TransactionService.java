package com.iFundi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.models.Transactions;
import com.iFundi.repositories.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public Transactions saveTransactions(Transactions transactions) {
		return transactionRepository.save(transactions);
	}

}
