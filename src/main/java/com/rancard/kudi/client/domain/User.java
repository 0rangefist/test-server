/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.domain;

import lombok.Data;

/*
 * Represents a registered client on Kudi who maintains
 * an account on the platform.
 */
@Data
public class User {

  /*
   * user's email
   */
  private String email;

  /*
   * user's password.this is managed by OpenAM
   */
  private String password;

  /*
   * user's mobile number
   */
  private int mobileNumber;

  /*
   * user's first name
   */
  private String firstName;

  /*
   * user's last name
   */
  private String lastName;

  /*
   * phone number country code prefix. Eg 233
   */
  private int countryCode;

  /*
   * 2-letter ISO country code to represent user's country. Eg GH
   */
  private String country;

  /*
   * unix timestamp of the user's last login
   */
  private int lastLogin;

  /*
   * check whether email is verified
   */
  private boolean emailVerified;

}
