package com.smallworld.repository.impl;

import com.smallworld.dto.Transaction;
import com.smallworld.repository.TransactionRepository;
import com.smallworld.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record TransactionRepositoryImpl(String filePath) implements TransactionRepository {

  @Override
  public List<Transaction> getAllTransactions() throws IOException {
    return FileUtils.getTransactionData(filePath);

  }

  @Override
  public List<Transaction> getTransactionsBySenderFullName(String senderFullName) throws IOException {
    return FileUtils.getTransactionData(filePath)
            .stream()
            .filter(transaction ->
                    Objects.equals(senderFullName,transaction.getSenderFullName()))
            .collect(Collectors.toList());
  }


  @Override
  public List<Transaction> getAllTransactionByClientName(String clientName) throws IOException {
    return  FileUtils.getTransactionData(filePath)
            .stream()
            .filter(transaction ->
                    Objects.equals(clientName,transaction.getBeneficiaryFullName())
                            || Objects.equals(clientName,transaction.getSenderFullName()))
            .collect(Collectors.toList());
  }
}
