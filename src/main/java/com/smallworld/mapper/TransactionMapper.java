package com.smallworld.mapper;

import com.smallworld.dto.Transaction;
import com.smallworld.entity.IssueDTO;
import com.smallworld.dto.TransactionDTO;

import java.util.List;
import java.util.Set;

public interface TransactionMapper {

   TransactionDTO mapTransactionToTransactionDTO(Transaction transaction);

   IssueDTO mapTransactionToIssue(Transaction transactionDTO);

   Set<TransactionDTO> mapTransactions(List<Transaction> transactions);

}
