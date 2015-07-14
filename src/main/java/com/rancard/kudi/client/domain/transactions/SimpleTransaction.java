/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain.transactions;

import lombok.Data;

@Data
public class SimpleTransaction extends Transaction {

  /*
   * account number to which the transactions performed is to be received
   */
  private int accountTo;
}
