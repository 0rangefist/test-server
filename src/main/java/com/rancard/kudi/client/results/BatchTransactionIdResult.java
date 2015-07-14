/**
 * Copyright (c) "Rancard solutions".
 */

package com.rancard.kudi.client.results;

import lombok.Data;

/**
 * Represents batch transaction on the KUDI platform.
 */

@Data
public class BatchTransactionIdResult implements KudiResult{
  /*
   Represent the batch id
   */
  private int batchId;
}
