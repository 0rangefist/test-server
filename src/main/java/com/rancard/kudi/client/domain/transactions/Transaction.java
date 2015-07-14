/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain.transactions;

import lombok.Data;

/*
 * Represents a unit of a transactions in Kudi. This means that more than
 * one record of the transactions of a particular id,must exist to guarantee
 * that the operation has been successfully applied.
 * By default performing a transaction is defined as paying for a service
 */
@Data
public abstract class Transaction {

  /*
   * globally unique transactions id
   */
  protected int transactionId;


  /*
   * a unique code generated according to the type of transactions,
   * to serve as a voucher code
   */
  protected int referenceCode;

  /*
   * the account number from which the transactions is to be performed
   */
  protected int accountFrom;

  /*
   * the amount involved in this transactions
   */
  protected double amount;

  /*
   * the charge applied to the transactions
   */
  protected double commission;

  /*
   * the actual amount credited or debited after deducting fee
   */
  protected double realAmount;

  /*
   * tax deducted from amont
   */
  protected double tax;

  /*
   * the event of this transactions.either of
   * 1.credit
   * 2.debit
   * depending on who is viewing the transactions
   */
  protected String event;

  /*
   * the state of the transactions.one of
   * 1.pending
   * 2.cancelled
   * 3.completed
   * */
  protected String state;

  /*
   * unix timestamp when the transactions started
   */
  protected int startedAt;

  /*
   * unix timestamp when transactions completed
   */
  protected int completedAt;

  /*
   * the class/type of this transactions.one of
   * 1.simple - K2K(Kudi to Kudi)
   * 2.payment - K2K using refCode
   * 3.transfer - between external reserve and Kudi
   */
  protected String type;

  /*
   * a user specified comment to describe the transactions.
   * a useful system generated one must be used as a default.
   */
  protected String comment;

  /*
   * a system generated message to describe the transaction
   */
  protected String note;
}
