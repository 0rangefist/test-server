package com.rancard.kudi.client.results;

/**
 * Copyright (c) 2015 "Rancard Solutions
 */
import lombok.Data;

/**
 * Represents create transaction result on the KUDI platform.
 */

@Data
public class CreateTransactionResult implements KudiResult{

  /*
    Represent a user's transaction id
   */
  private int transactionId;

  /*
  Represent a user's account number
   */
  private int accountFrom;

  /*
  Represent the amount that is involved in the transaction
   */
  private double amount;

  /*
  Represent the reference code
   */
  private int refCode;

  /*
  Represent unix timestamp when the transaction started
   */
  private int startedAt;

  /*
  Represent the state of the transaction
   */
  private String state;

  /*
  Represent the status
   */
  private int status;
}
