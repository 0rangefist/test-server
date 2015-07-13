package com.rancard.kudi.developer;

/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

import com.rancard.kudi.client.Kudi;
import com.rancard.kudi.client.Session;
import com.rancard.kudi.client.exceptions.KudiException;
import com.rancard.kudi.client.results.LoginResult;
import com.rancard.kudi.client.domain.User;

public class DeveloperSandBox {
/**
 * Developer SandBox. Used to test the Kudi API.
 */
  public static void main(String[] args) {
    String[] userName = new String[5];
    userName[0] = "collinskthomas@gmail.com";
    userName[1] = "tettehmichael300@gmail.com";
    userName[2] = "incorrect_email@gmail.com";
    userName[3] = "server_down@gmail.com";
    userName[4] = "unauthorised@gmail.com";

    //Every Client must provide an appKey

    // Use the Kudi Provided User Data Type to store user info
    User sessionUser = new User();
    sessionUser.setEmail(userName[2]);
    sessionUser.setPassword("randomPass123");

    // Run the instance of the Kudi client
    Kudi testClient = new Kudi();


    try{
      // Attempt to login
      LoginResult myResponse = testClient.login(sessionUser);

      // Print out a response obtained after performing a login
      System.out.println(myResponse.getUser().getFirstName());

      // Create a session for the current user using the retrieved token
      Session se = testClient.getSession(myResponse.getToken());

      // Logout of the current session
      se.logout();
    } catch (KudiException ke) {
      ke.printStackTrace();
      System.out.println("Error Code: " + ke.getErrorCode());
    } catch (Exception e) {
      //suppress other java exceptions
    }

    //Session m = new Kudi().getSession("fakeToken");
    //m.logout();
  }
}
