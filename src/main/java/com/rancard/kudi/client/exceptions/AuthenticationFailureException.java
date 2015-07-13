/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.exceptions;

import org.apache.http.HttpStatus;

public class AuthenticationFailureException extends KudiException {

  public AuthenticationFailureException(String message) {
    super(message);
    this.errorCode = HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED;
  }
}
