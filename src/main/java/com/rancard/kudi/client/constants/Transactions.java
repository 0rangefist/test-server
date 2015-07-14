/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.constants;

import com.rancard.kudi.client.domain.transactions.PaymentTransaction;
import com.rancard.kudi.client.domain.transactions.SimpleTransaction;
import com.rancard.kudi.client.domain.transactions.Transaction;

public class Transactions {
  public static final Transaction[] simpleTransactions = new SimpleTransaction[5];
  public static final Transaction[] paymentTransactions = new PaymentTransaction[5];
  public static final SimpleTransaction st = new SimpleTransaction();
  public static final PaymentTransaction pt = new PaymentTransaction();
  private int var = 10;
  private Transactions(){
    for(int i = 0; i<5; i++)
    {
      simpleTransactions[i].setAccountFrom(100 );
      simpleTransactions[i].setAmount(500);
      simpleTransactions[i].setComment("Sent money to Bonni for his birthday.");
      simpleTransactions[i].setCommission(0.01*simpleTransactions[0].getAmount());
      simpleTransactions[i].setTax(0.02*simpleTransactions[0].getAmount());
      simpleTransactions[i].setRealAmount(simpleTransactions[0].getAmount()
          + simpleTransactions[0].getTax() + simpleTransactions[0].getCommission());
      simpleTransactions[i].setNote("Kudi to Kudi Transfer");
      simpleTransactions[i].setEvent("Debit");
      simpleTransactions[i].setReferenceCode(124 * (var/100));
      simpleTransactions[i].setState("Completed");
      simpleTransactions[i].setTransactionId(12237 + var);
      simpleTransactions[i].setStartedAt(1631120715);
      simpleTransactions[i].setCompletedAt(1901130715);
      var = var + 20;
    }
    for(int i = 0; i<5; i++)
    {
      paymentTransactions[i].setAccountFrom(100 );
      paymentTransactions[i].setAmount(10);
      paymentTransactions[i].setComment("");
      paymentTransactions[i].setCommission(0.01 * simpleTransactions[0].getAmount());
      paymentTransactions[i].setTax(0.02 * simpleTransactions[0].getAmount());
      paymentTransactions[i].setRealAmount(simpleTransactions[0].getAmount()
          + simpleTransactions[0].getTax() + simpleTransactions[0].getCommission());
      paymentTransactions[i].setNote("Kudi to Vendor Transfer");
      simpleTransactions[i].setEvent("Debit");
      simpleTransactions[i].setReferenceCode(126 * (var/100));
      simpleTransactions[i].setState("Completed");
      simpleTransactions[i].setTransactionId(10233 + var);
      simpleTransactions[i].setStartedAt(1631120715);
      simpleTransactions[i].setCompletedAt(1901130715);
      var = var + 20;
    }

  }
}
