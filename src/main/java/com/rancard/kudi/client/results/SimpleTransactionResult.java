/**
 * Copyright (c) "Rancard solutions".
 */

package com.rancard.kudi.client.results;
import lombok.Data;

/**
 * Represents payment transfer funds between Kudi accounts
 on the KUDI platform.
 */

@Data
public class SimpleTransactionResult implements KudiResult {
  /*
   Represent a user's transaction id
  */
  private int transactionId;

  /*
Represent the reference code
 */
  private int referenceCode;

  /*
  Represent where the account is from
   */
  private int accountFrom;

  /*
  Represent where the account is coming from
   */
  private int accountTo;

  /*
  Represent the amount that is involved in the transaction
  */
  private double amount;

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
