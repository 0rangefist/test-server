/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.results;

import com.rancard.kudi.client.domain.User;

public class LoginResult implements KudiResult{

  private String token;
  private User user;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
