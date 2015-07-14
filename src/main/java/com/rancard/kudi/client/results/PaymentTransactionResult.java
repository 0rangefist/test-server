/**
 * Copyright (c) "Rancard solutions".
 */

package com.rancard.kudi.client.results;

import lombok.Data;

/**
 * Represents payment transaction result on the KUDI platform.
 */

@Data
public class PaymentTransactionResult implements KudiResult {
  /*
     Represent a user's transaction id
    */
  private int transactionId;

  /*
  Represent where the account is from
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
  pending, cancelling, completed
   */
  private String state;
}
