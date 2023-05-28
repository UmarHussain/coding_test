package com.smallworld.mapper.impl;

import com.smallworld.dto.Transaction;
import com.smallworld.dto.TransactionDTO;
import com.smallworld.entity.IssueDTO;
import com.smallworld.mapper.TransactionMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionMapperImpl implements TransactionMapper {

  @Override
  public TransactionDTO mapTransactionToTransactionDTO(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setMtn(transaction.getMtn());
    transactionDTO.setAmount(transaction.getAmount());
    transactionDTO.setSenderAge(transaction.getSenderAge());
    transactionDTO.setSenderFullName(transaction.getSenderFullName());
    transactionDTO.setBeneficiaryAge(transaction.getBeneficiaryAge());
    transactionDTO.setBeneficiaryFullName(transaction.getBeneficiaryFullName());
    transactionDTO.getIssues().add(mapTransactionToIssue(transaction));

    return transactionDTO;
  }

  @Override
  public IssueDTO mapTransactionToIssue(Transaction transaction) {
    IssueDTO issueDTO = new IssueDTO();
    issueDTO.setIssueId(transaction.getIssueId());
    issueDTO.setIssueSolved(transaction.getIssueSolved());
    issueDTO.setIssueMessage(transaction.getIssueMessage());
    return issueDTO;
  }

  @Override
  public Set<TransactionDTO> mapTransactions(List<Transaction> transactions) {
    Map<Integer,TransactionDTO> transactionDTOMap = new HashMap<>();

    transactions.forEach(
        transaction -> {
          if (transactionDTOMap.containsKey(transaction.getMtn())) {
            TransactionDTO transactionDTO = transactionDTOMap.get(transaction.getMtn());
            transactionDTO.getIssues().add(mapTransactionToIssue(transaction));
          }else{
            transactionDTOMap.put(transaction.getMtn(),mapTransactionToTransactionDTO(transaction));
          }

        });
    return new HashSet<>(transactionDTOMap.values());
  }
}
