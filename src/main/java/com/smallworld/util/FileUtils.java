package com.smallworld.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.dto.Transaction;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

  private FileUtils() {}

  public static List<Transaction> getTransactionData(String filePath) throws IOException {

    List<Transaction> transactionDTOS =
        Arrays.asList(
            new ObjectMapper().readValue(Paths.get(filePath).toFile(), Transaction[].class));
    return transactionDTOS;
  }
}
