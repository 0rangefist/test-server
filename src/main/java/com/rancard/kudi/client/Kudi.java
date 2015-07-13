package com.rancard.kudi.client;
/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

import com.rancard.kudi.client.exceptions.AuthenticationFailureException;
import com.rancard.kudi.client.exceptions.BadRequestException;
import com.rancard.kudi.client.exceptions.InternalServerErrorException;
import com.rancard.kudi.client.exceptions.KudiException;
import com.rancard.kudi.client.results.LoginResult;
import com.rancard.kudi.client.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class Kudi {
  /* Jersey Client fields */
  private ClientConfig clientConfig;
  private Client client;
  private WebResource webResource;
  private ClientResponse response;

  /**
   * Kudi constructor. Creates a jersey client and a custom
   * client configuration with POJO mapping enabled.
   * The mapping implements auto serialization/de-serialization
   * of objects passed/received over http to/from JSON
   */
  public Kudi() {
    clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures()
                .put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    client = Client.create(clientConfig);
  }

  /**
   * getSession method. This is called by the client and
   * allows a user establish a new session based on a token.
   *
   *  @param token a string that contains an authorization
   *               token obtained when a user logs in to Kudi.
   *  @retun a Session Object which lets an authorized user
   *         perform more Kudi actions.
   */
  public Session getSession(String token) {
    Session currentSession = new Session(token);

    return currentSession;
  }

  /**
   * login method. This is called by the client and
   * allows a user to login to Kudi.
   */
  public LoginResult login(User user) throws KudiException{
    LoginResult myResponse;
    try {
      webResource = client
          .resource("http://localhost:8080/api/v1/login");
      response = webResource
          .accept("application/json")
          .type("application/json")
          .post(ClientResponse.class, user);

      if (response.getStatus() == 400) {
        throw new BadRequestException("Wrong Argument Exception");
      } else if (response.getStatus() == 401) {
        throw new AuthenticationFailureException("Authentication Failed");
      } else if (response.getStatus() == 500) {
        throw new InternalServerErrorException("Server Down Exception");
      }


      //System.out.println("Server response .... \n");
      //System.out.println(output);
    } catch (KudiException ke) {
      throw ke;
    }
    myResponse = response.getEntity(LoginResult.class);
    return myResponse;
  }

}