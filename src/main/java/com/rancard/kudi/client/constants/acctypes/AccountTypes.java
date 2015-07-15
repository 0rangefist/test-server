/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.constants.acctypes;

import com.rancard.kudi.client.domain.AccountType;

public class AccountTypes {
  public static final AccountType KUDI = new AccountType();
  public static final  AccountType SAVINGS = new AccountType();
  public static final  AccountType CURRENT = new AccountType();

  protected AccountTypes(){
    KUDI.setName("Kudi");
    KUDI.setId(AccountTypeIds.KUDI);
    KUDI.setCategory("Africa");
    KUDI.setMinimumBalance(10);
    KUDI.setInterestRate(1);
    KUDI.setNegativeBalanceAllowed(false);
    KUDI.setInterestCompoundFrequency(30);
    KUDI.setAccountFeeId(1);

    SAVINGS.setName("Savings");
    SAVINGS.setId(AccountTypeIds.KUDI);
    SAVINGS.setCategory("Africa");
    SAVINGS.setMinimumBalance(50);
    SAVINGS.setInterestRate(5);
    SAVINGS.setNegativeBalanceAllowed(false);
    SAVINGS.setInterestCompoundFrequency(30);
    SAVINGS.setAccountFeeId(1);

    CURRENT.setName("Savings");
    CURRENT.setId(AccountTypeIds.KUDI);
    CURRENT.setCategory("Africa");
    CURRENT.setMinimumBalance(0);
    CURRENT.setInterestRate(0);
    CURRENT.setNegativeBalanceAllowed(true);
    CURRENT.setInterestCompoundFrequency(0);
    CURRENT.setAccountFeeId(1);

  }

}
