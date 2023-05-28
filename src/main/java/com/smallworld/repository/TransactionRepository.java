package com.smallworld.repository;

import com.smallworld.dto.Transaction;

import java.io.IOException;
import java.util.List;

/***
 * This Repository is use get the transactions from data set either on file or database,
 * currently only file base implementation is provided
 * that is available in resources/transactions.json
 */
public interface TransactionRepository {

  List<Transaction> getAllTransactions() throws IOException;
  List<Transaction> getTransactionsBySenderFullName(String senderFullName) throws IOException;

  /***
   * This these transactions could be by sender full name or beneficiary full name
   * @param clientName
   * @return
   */
  List<Transaction> getAllTransactionByClientName(String clientName) throws IOException;

}
