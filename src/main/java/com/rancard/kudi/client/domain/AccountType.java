/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain;

import lombok.Data;

/*
 * Represent the various types of account supported on Kudi
 * per country
 */
@Data
public class AccountType {

  /*
   * unique database id
   */
  private int id;

  /*
   * name of this account type
   */
  private String name;

  /*
   * category of this account type to cluster types across countries
   */
  private String category;

  /*
   * minimum balance allowed
   */
  private double minimumBalance;

  /*
   * 2-letter ISO country code.Eg GH
   */
  private String country;

  /*
   * interest rate per annum
   */
  private double interestRate;

  /*
   * flag if negative balance is allowed
   */
  private boolean negativeBalanceAllowed;

  /*
   * number of days for compounding interest
   */
  private int interestCompoundFrequency;

  /*
   * reference to the applied fees
   */
  private int acccountFeeId;
}
