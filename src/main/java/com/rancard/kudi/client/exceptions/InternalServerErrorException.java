/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.exceptions;

import org.apache.http.HttpStatus;

public class InternalServerErrorException extends KudiException {

  public InternalServerErrorException(String message) {
    super(message);
    this.errorCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;
  }
}
