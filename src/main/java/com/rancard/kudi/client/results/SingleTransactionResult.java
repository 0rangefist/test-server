/**
 * Copyright (c) "Rancard solutions".
 */

package com.rancard.kudi.client.results;
import lombok.Data;

/**
 * Represents single transaction associated with user on the KUDI platform.
 */

@Data
public class SingleTransactionResult implements KudiResult {
  /*
  Represent a user's transaction id
   */
  private int transactionId;

  /*
  Represent the state of transaction
  pending, cancelled, completed
   */
  private String state;

  /*
  Represent a unique code generated according to according to
  the type of this transaction, to serve as a voucher code.
   */
  private int referenceCode;

  /*
  Represent amount involved in the transaction
   */
  private double amount;

  /*
  Represent the actual amount remaining after taking out charges.
   */
  private double realAmount;

  /*
  Represent the account Number involved in the transaction
   */
  private int accountFrom;

  /*
  Represent the commission charged on this transaction
   */
  private int commission;

  /*
  Represent the tax charged on this transaction.
   */
  private int tax;

  /*
  Represent a user specified comment to describe the transaction.
   */
  private String comment;

  /*
  Represent a system generated message to describe the transaction.
   */
  private String note;

  /*
  Represent the event of this transaction, either of
  credit or debit
  */

  private String event;

  /*
  Represent the class/type of this transaction.
  simple, payment, transfer
   */
  private String type;

  /*
  Represent the unix timestamp when the transaction started
   */
  private int startedAt;

  /*
  Represent the unix timestamp when the transaction completed
   */
  private int completedAt;



}

