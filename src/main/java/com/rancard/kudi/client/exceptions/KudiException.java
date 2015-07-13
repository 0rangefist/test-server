/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.exceptions;

public class KudiException extends Exception {

  protected String message;

  protected int errorCode;

  /**
   * The constructor of this Exception Class takes the following special arguments.
   * These arguments are important to the com.rancard.kudi.developer.
   *
   * @param errorCode application specific error errorCode.
   * @param message  error description for developers.
   */
  public KudiException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public KudiException(String message) {
    super(message);
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

}
