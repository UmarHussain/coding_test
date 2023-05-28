package com.smallworld.service;

import com.smallworld.dto.Transaction;
import com.smallworld.dto.TransactionDTO;
import com.smallworld.mapper.TransactionMapper;
import com.smallworld.repository.TransactionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TransactionDataFetcher {


  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;


  public TransactionDataFetcher(
      TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  /** Returns the sum of the amounts of all transactions */
  public Double getTotalTransactionAmount() throws IOException {
    List<Transaction> transactions = transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return 0.0;
    }
    return transactionMapper.mapTransactions(transactions).stream()
        .mapToDouble(TransactionDTO::getAmount)
        .sum();
  }

  /** Returns the sum of the amounts of all transactions sent by the specified client */
  public Double getTotalTransactionAmountSentBy(String senderFullName) throws IOException {
    List<Transaction> transactions =
        transactionRepository.getTransactionsBySenderFullName(senderFullName);
    if (transactions == null || transactions.isEmpty()) {
      return 0.0;
    }
    return transactionMapper.mapTransactions(transactions).stream()
        .mapToDouble(TransactionDTO::getAmount)
        .sum();
  }

  /** Returns the highest transaction amount */
  public Double getMaxTransactionAmount() throws IOException {
    Double maxTransaction = 0.0;
    List<Transaction> transactions = transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return maxTransaction;
    }
    Optional<TransactionDTO> transactionDTO =
        transactionMapper.mapTransactions(transactions).stream()
            .max(Comparator.comparing(TransactionDTO::getAmount));

    if (transactionDTO.isPresent()) {
      maxTransaction = transactionDTO.get().getAmount();
    }
    return maxTransaction;
  }

  /** Counts the number of unique clients that sent or received a transaction */
  public Integer countUniqueClients() throws IOException {
    List<Transaction> transactions = transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return 0;
    }
    Set<String> names = new HashSet<>();
    transactions.forEach(
        transaction -> {
          names.add(transaction.getBeneficiaryFullName());
          names.add(transaction.getSenderFullName());
        });
    return names.size();
  }

  /**
   * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
   * issue that has not been solved
   */
  public boolean hasOpenComplianceIssues(String clientFullName) throws IOException {
    List<Transaction> transactions =
            transactionRepository.getAllTransactionByClientName(clientFullName);
    if (transactions == null || transactions.isEmpty()) {
      return false;
    }
    return  transactions.stream().anyMatch(transaction -> !transaction.getIssueSolved());
  }

  /**
   * Returns all transactions indexed by beneficiary name
   * @return Map<String, Set<TransactionDTO>>
   *   */
  public Map<String, Set<TransactionDTO>> getTransactionsByBeneficiaryName() throws IOException {
    List<Transaction> transactions =
            transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return Collections.emptyMap();
    }
    return transactionMapper.mapTransactions(transactions).stream()
        .collect(Collectors.groupingBy(TransactionDTO::getBeneficiaryFullName,Collectors.toSet()));
  }

  /**
   *  Returns the identifiers of all open compliance issues
   *  */
  public Set<Integer> getUnsolvedIssueIds() throws IOException {
    List<Transaction> transactions =
            transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return Collections.emptySet();
    }
    Set<Integer> ids = new HashSet<>();
    transactions.forEach(transaction -> {
        if(transaction.getIssueId()!= null && !transaction.getIssueSolved()){
          ids.add(transaction.getIssueId());
        }
    });
    return ids;
  }

  /** Returns a list of all solved issue messages */
  public List<String> getAllSolvedIssueMessages() throws IOException {
    List<Transaction> transactions =
            transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return Collections.emptyList();
    }
    List<String> messages = new ArrayList<>();
    transactions.forEach(
        transaction -> {
          if (Boolean.TRUE.equals(transaction.getIssueSolved())) {
            messages.add(transaction.getIssueMessage());
          }
        });
    return messages;
  }

  /** Returns the 3 transactions with the highest amount sorted by amount descending */
  public List<TransactionDTO> getTop3TransactionsByAmount() throws IOException {
    List<Transaction> transactions =
            transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return Collections.emptyList();
    }

    return transactionMapper.mapTransactions(transactions).stream()
        .sorted(Comparator.comparing(TransactionDTO::getAmount).reversed())
        .limit(3)
        .collect(Collectors.toList());
  }

  /** Returns the sender with the most total sent amount */
  public String getTopSender() throws IOException {
    AtomicReference<String> topSender = new AtomicReference<>("");
    AtomicReference<Double> highestAmount = new AtomicReference<>(0.0);
    List<Transaction> transactions =
            transactionRepository.getAllTransactions();
    if (transactions == null || transactions.isEmpty()) {
      return topSender.get();
    }
    Map<String,Set<TransactionDTO>> map =  transactionMapper.mapTransactions(transactions).stream()
            .collect(Collectors.groupingBy(TransactionDTO::getSenderFullName,Collectors.toSet()));

    map.forEach((senderName, transactionDTOS) -> {
      Double sumOfCurrentTransaction = transactionDTOS.stream().mapToDouble(TransactionDTO::getAmount).sum();
      if(sumOfCurrentTransaction>highestAmount.get()){
        highestAmount.set(sumOfCurrentTransaction);
        topSender.set(senderName);
      }
    });
    return topSender.get();
  }
}
