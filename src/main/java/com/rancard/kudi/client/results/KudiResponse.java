/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.results;

import lombok.Data;

@Data
public class KudiResponse {

  private String message;
  private int code;
  private int status;
  private KudiResult result;

}
