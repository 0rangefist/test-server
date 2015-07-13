package com.rancard.kudi.service;

/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

import com.rancard.kudi.client.constants.HeaderParameter;
import com.rancard.kudi.client.constants.StatusCode;
import com.rancard.kudi.client.results.EmptyResult;
import com.rancard.kudi.client.results.ExchangeRatesResult;
import com.rancard.kudi.client.results.KudiResponse;
import com.rancard.kudi.client.results.LoginResult;
import com.rancard.kudi.client.domain.User;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Random;

/*
 * Dummy Server to test Kudi Client functionality
 */
@Path("/api/v1")
public class KudiServiceSimulation {
  private String randomToken = "--I was generated in the Kudi Server--";
  /**
   * Login method. This method is called by the client and
   * allows the client to login to the server.
   * The input argument is a user type, which contains a user's email and password.
   * The method returns a user type as well which includes the rest of the user's profile.
   *
   * @param user an object that contains email and password from the client.
   * @return the response which contains the rest of the user's profile plus a token.
   */
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(User user) throws JSONException, IllegalArgumentException {

    //create a response and result object
    KudiResponse loginResponse = new KudiResponse();
    LoginResult loginResult = new LoginResult();

    //simulating a server's database with certain authorized members
    //and various server conditions/states.
    try {
      if (user.getEmail().equals("collinskthomas@gmail.com")) //simulating different users
      {
        user.setFirstName("Thomas"); //simulating data obtained from database
        user.setLastName("Collins");
        user.setMobileNumber(0253434543);
        user.setCountry("GH");
        user.setCountryCode(233);
        user.setLastLogin(1507031635);
        user.setPassword(null);
      } else if (user.getEmail().equals("tettehmichael300@gmail.com")) //simulating another user
      {
        user.setFirstName("Michael");
        user.setLastName("Tetteh");
        user.setMobileNumber(0245345344);
        user.setCountry("UK");
        user.setCountryCode(44);
        user.setLastLogin(1507031636);
        user.setPassword(null);
      } else if (user.getEmail().equals("unauthorised@gmail.com")) {
        //set all the parameters for a unsuccessful login
        loginResponse.setMessage("Login unsuccessful");
        loginResponse.setCode(StatusCode.USER_ERROR);
        loginResponse.setStatus(401);
        loginResponse.setResult(loginResult);
        return Response.status(401).entity(loginResponse).build();
      } else if (user.getEmail().equals("server_down@gmail.com")) {
        //set all the parameters for a unsuccessful login
        loginResponse.setMessage("Login unsuccessful");
        loginResponse.setCode(StatusCode.SYSTEM_ERROR);
        loginResponse.setStatus(500);
        loginResponse.setResult(loginResult);
        return Response.status(500).entity(loginResponse).build();
      } else {
        //set all the parameters for a unsuccessful login
        loginResponse.setMessage("Login unsuccessful");
        loginResponse.setCode(StatusCode.USER_ERROR);
        loginResponse.setStatus(400);
        loginResponse.setResult(loginResult);
        return Response.status(400).entity(loginResponse).build();
      }
    } catch (Exception e) {
      System.out.print(e);
    }

    //add the unique token to the loginResult
    randomToken = randomToken + user.getFirstName() + String.valueOf(new Random().nextInt(100) + 10);
    loginResult.setToken(randomToken);

    //add the user data to the loginResult
    loginResult.setUser(user);

    //set all the parameters for a successful login
    loginResponse.setMessage("Login Successful");
    loginResponse.setCode(StatusCode.SUCCESS);
    loginResponse.setStatus(200);
    loginResponse.setResult(loginResult);
    return Response.status(200).entity(loginResponse).build();
    //return userLoginResponse;
  }

  @GET
  @Path("/logout")
  @Produces(MediaType.APPLICATION_JSON)
  public Response logout(@HeaderParam(HeaderParameter.KUDI_TOKEN) String sessionToken) throws JSONException, IllegalArgumentException {
    String output;

    //create a response object
    KudiResponse logoutResponse = new KudiResponse();

    if(sessionToken.contains("I was generated in the Kudi Server")) {
      if(sessionToken.contains("Thomas")) {
        output = "Logout Successful\nCatch u l8r, Tommy";
      }
      else if(sessionToken.contains("Michael")) {
        output = "Logout Successful\nSee ya later, Mike";
      }
      else {
        output = "Logout Successful";
      }

      //set all the parameters for a successful logout
      logoutResponse.setMessage(output);
      logoutResponse.setCode(StatusCode.SUCCESS);
      logoutResponse.setStatus(200);
      logoutResponse.setResult(null);
      return Response.status(200).entity(logoutResponse).build();
    }

    //set all the parameters for an unsuccessful logout
    logoutResponse.setMessage("Logout failed");
    logoutResponse.setCode(StatusCode.SYSTEM_ERROR);
    logoutResponse.setStatus(500);
    logoutResponse.setResult(new EmptyResult());

    return Response.status(500).build();
  }

  @GET
  @Path("/rates")
  @Produces(MediaType.APPLICATION_JSON)
  public Response logout() throws JSONException, IllegalArgumentException {

    //create a response and result object
    KudiResponse ratesResponse = new KudiResponse();
    ExchangeRatesResult ratesResult = new ExchangeRatesResult();
    HashMap<String, Double> rates = new HashMap<String, Double>();
    rates.put("GH",3.5);
    rates.put("CA", 1.25);
    rates.put("ZA", 12.5);
    ratesResult.setRates(rates);

    //set all the parameters for the exchange rates response
    ratesResponse.setMessage("Rates Acquired");
    ratesResponse.setCode(StatusCode.SUCCESS);
    ratesResponse.setStatus(200);
    ratesResponse.setResult(ratesResult);

    return Response.status(200).entity(ratesResponse).build();
  }
}