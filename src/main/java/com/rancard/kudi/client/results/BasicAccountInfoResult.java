/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.results;


import lombok.Data;

@Data
public class BasicAccountInfoResult implements KudiResult {
  /*
  * Represents a user's multiple accounts results
 */
  private int accountNumber;

  /*
   * Represent a User's name during account creation on KUDI platform
   */
  private String accountName;

  /*
   * Represent a User's current balance
   */
  private double currentBalance;

  /*
  * Represent a User's previous balance
  */
  private double previousBalance;

  /*
   * Represent the account type id of the user
   */
  private int typeId;
}
