/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain;

import lombok.Data;

/*
 * Represents a Kudi account,that is, a reserve for funds
 * from which transactions may be performed
 */
@Data
public class Account {

  /*
   * name of the account
   */
  private String accountName;

  /*
   * globally unique account number
   */
  private int accountNumber;

  /*
   * the current account balance.balance after latest transactions
   */
  private double currentBalance;

  /*
   * the previous account balance.balance before lastest transactions
   */
  private double previousBalance;

  /*
   * 3-letter ISO currency code. Eg GHS
   */
  private String currency;

  /*
   * owner id of the account
   */
  private int ownerId;

  /*
   * account type
   */
  private AccountType accountType;

  /*
   * check whether account is active
   */
  private boolean isActive;

  /*
   * JSON serialized string of extra properties per account
   */
  private String metaData;
}
