/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client;

import com.rancard.kudi.client.constants.HeaderParameters;
import com.rancard.kudi.client.exceptions.AuthenticationFailureException;
import com.rancard.kudi.client.exceptions.BadRequestException;
import com.rancard.kudi.client.exceptions.InternalServerErrorException;
import com.rancard.kudi.client.exceptions.KudiException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class Session {

  private String token;
  private ClientConfig clientConfig;
  private Client client;
  private WebResource webResource;
  private ClientResponse response;

  Session(String token) {
    clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures()
        .put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    client = Client.create(clientConfig);
    this.token = token;
  }

  /**
   * logout method. This is called by the client and
   * allows a user to logout of the current session.
   */
  public void logout() throws KudiException{
    try {
      webResource = client
          .resource("http://localhost:8080/api/v1/logout");
      response = webResource
          .accept("application/json")
          .type("application/json")
          .header(HeaderParameters.KUDI_TOKEN, token)
          .get(ClientResponse.class);

      if (response.getStatus() == 400) {
        throw new BadRequestException("Wrong Argument Exception");
      } else if (response.getStatus() == 401) {
        throw new AuthenticationFailureException("Authentication Failed");
      } else if (response.getStatus() == 500) {
        throw new InternalServerErrorException("Server Down Exception");
      }

      String logoutMessage = response.getEntity(String.class);
      System.out.print(logoutMessage);
      //System.out.println("Server response .... \n");
      //System.out.println(output);
    } catch (KudiException ke) {
      throw ke;
    }
  }
}
