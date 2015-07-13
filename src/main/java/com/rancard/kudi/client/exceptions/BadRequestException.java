/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.exceptions;

import org.apache.http.HttpStatus;

public class BadRequestException extends KudiException {

  public BadRequestException(String message) {
    super(message);
    this.errorCode = HttpStatus.SC_BAD_REQUEST;
  }
}
