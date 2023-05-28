package com.smallworld;

import com.smallworld.dto.TransactionDTO;
import com.smallworld.mapper.TransactionMapper;
import com.smallworld.mapper.impl.TransactionMapperImpl;
import com.smallworld.repository.TransactionRepository;
import com.smallworld.repository.impl.TransactionRepositoryImpl;
import com.smallworld.service.TransactionDataFetcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TestCases {

  private final TransactionDataFetcher transactionDataFetcher;

  {
    TransactionRepository transactionRepository = new TransactionRepositoryImpl("transactions.json");
    TransactionMapper transactionMapper = new TransactionMapperImpl();
    transactionDataFetcher = new TransactionDataFetcher(transactionRepository, transactionMapper);
  }

  @Test
  void getTotalTransactionAmount() throws IOException {
    Double amount = transactionDataFetcher.getTotalTransactionAmount();
    Assertions.assertTrue(amount>0.0);
  }

  @Test
  void getTotalTransactionAmountSentByNotMentionedInJsonFile() throws IOException {
    Double amount = transactionDataFetcher.getTotalTransactionAmountSentBy("abc");
    Assertions.assertEquals(0.0, amount);
  }

  @Test
  void getTotalTransactionAmountSentForValidSender() throws IOException {
    Double amount = transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
    Assertions.assertTrue(amount > 0.0);
  }


  @Test
  void getMaxTransactionAmount() throws IOException {
    Double amount = transactionDataFetcher.getMaxTransactionAmount();
    Assertions.assertEquals(985.0,amount);
  }

  @Test
  void countUniqueClients() throws IOException {
    Integer countUniqueClients = transactionDataFetcher.countUniqueClients();
    System.out.println(countUniqueClients);
    Assertions.assertEquals(14,countUniqueClients);
  }

  @Test
  void hasOpenComplianceIssues() throws IOException {
     boolean openIssues = transactionDataFetcher.hasOpenComplianceIssues("Aberama Gold");
    Assertions.assertFalse(openIssues);
  }

  @Test
  void getUnsolvedIssueIds() throws IOException {
    Set<Integer> ids = transactionDataFetcher.getUnsolvedIssueIds();
    Assertions.assertTrue(ids.contains(3));
  }

  @Test
  void getTransactionsByBeneficiaryName() throws IOException {
    Map<String, Set<TransactionDTO>> transactionsByBeneficiaryName =
        transactionDataFetcher.getTransactionsByBeneficiaryName();

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setMtn(1284564);
    transactionDTO.setAmount(150.2);
    transactionDTO.setSenderAge(22);
    transactionDTO.setSenderFullName("Tom Shelby");
    transactionDTO.setBeneficiaryAge(60);
    transactionDTO.setBeneficiaryFullName("Arthur Shelby");
    Assertions.assertTrue(transactionsByBeneficiaryName.get("Arthur Shelby").contains(transactionDTO));
  }

  @Test
  void getAllSolvedIssueMessages() throws IOException {
    List<String> messages =  transactionDataFetcher.getAllSolvedIssueMessages();
    Assertions.assertTrue(messages.contains("Never gonna give you up"));
  }

  @Test
  void getTop3TransactionsByAmount() throws IOException {
    List<TransactionDTO> transactions =  transactionDataFetcher.getTop3TransactionsByAmount();

    Assertions.assertEquals(985.0, transactions.get(0).getAmount());
    Assertions.assertEquals(666.0, transactions.get(1).getAmount());
    Assertions.assertEquals(430.2, transactions.get(2).getAmount());
  }

  @Test
  void getTopSender() throws IOException {
    String topSender =  transactionDataFetcher.getTopSender();
    System.out.println(topSender);
    Assertions.assertEquals("Arthur Shelby",topSender);
  }
}
