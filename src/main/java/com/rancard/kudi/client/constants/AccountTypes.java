/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.constants;

import com.rancard.kudi.client.domain.AccountType;

public class AccountTypes {
  public static AccountType KUDI = new AccountType();
  public static AccountType SAVINGS = new AccountType();
  public static AccountType CURRENT =  new AccountType();

  private AccountTypes(){
    KUDI.setName("Kudi");
    SAVINGS.setName("Savings");
    CURRENT.setName("Current");
  }

}
