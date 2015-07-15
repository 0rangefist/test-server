/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.results;

import lombok.Data;

import java.util.List;

@Data
public class AccountListResponse {

  private String message;
  private int code;
  private int status;
  private List<BasicAccountInfoResult> result;
}
