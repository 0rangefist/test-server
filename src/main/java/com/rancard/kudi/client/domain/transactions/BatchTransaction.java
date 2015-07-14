/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain.transactions;

import lombok.Data;

@Data
public class BatchTransaction extends Transaction {

  /*
   * accounts to be used in order for crediting target accounts.
   * account are tried in order and progress upon insufficient
   * funds.
   */
  private int accounts;

}
