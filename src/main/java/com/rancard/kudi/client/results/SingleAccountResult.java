/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.results;

import com.rancard.kudi.client.domain.transactions.Transaction;
import lombok.Data;

import java.util.Map;

/**
 * Represents a user's result after creating an account on the KUDI platform.
 */
@Data
public class SingleAccountResult implements  KudiResult{
  /*
   * Represents a user's account number after creating an account
  */
  private int accountNumber;

  /*
   * Represent a User's name during account creation on KUDI platform
   */
  private String accountName;

  /*
  Represent account type id
   */

  private double currentBalance;

  /*
  * Represent a User's previous balance
  */
  private double previousBalance;

  /*
   * Represent the account type of the user
   */
  private int typeId;

  /*
   *Represent a user's last transaction
   */
  private Transaction lastTransaction;

  /*
   * This may key-values which override corresponding keys defined in the accountType
   */
  private Map metaData;


}
