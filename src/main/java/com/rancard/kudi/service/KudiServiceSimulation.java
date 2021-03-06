package com.rancard.kudi.service;

/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

import com.rancard.kudi.client.constants.HeaderParameters;
import com.rancard.kudi.client.constants.StatusCodes;
import com.rancard.kudi.client.constants.Transactions;
import com.rancard.kudi.client.constants.acctypes.AccountTypeIds;
import com.rancard.kudi.client.domain.Account;
import com.rancard.kudi.client.domain.User;
import com.rancard.kudi.client.domain.transactions.BatchTransaction;
import com.rancard.kudi.client.domain.transactions.PaymentTransaction;
import com.rancard.kudi.client.domain.transactions.SimpleTransaction;
import com.rancard.kudi.client.results.*;
import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
 * Dummy Server to test Kudi Client functionality
 */
@Path("/api/v1")
public class KudiServiceSimulation {
  private String accName;
  private int accTypeId;
  KudiResponse createAccountResponse;
  private SingleAccountResult singleAccountResult;
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
        loginResponse.setCode(StatusCodes.USER_ERROR);
        loginResponse.setStatus(401);
        loginResponse.setResult(loginResult);
        return Response.status(401).entity(loginResponse).build();
      } else if (user.getEmail().equals("server_down@gmail.com")) {
        //set all the parameters for a unsuccessful login
        loginResponse.setMessage("Login unsuccessful");
        loginResponse.setCode(StatusCodes.SYSTEM_ERROR);
        loginResponse.setStatus(500);
        loginResponse.setResult(loginResult);
        return Response.status(500).entity(loginResponse).build();
      } else {
        //set all the parameters for a unsuccessful login
        loginResponse.setMessage("Login unsuccessful");
        loginResponse.setCode(StatusCodes.USER_ERROR);
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
    loginResponse.setCode(StatusCodes.SUCCESS);
    loginResponse.setStatus(200);
    loginResponse.setResult(loginResult);
    return Response.status(200).entity(loginResponse).build();
    //return userLoginResponse;
  }

  @GET
  @Path("/logout")
  @Produces(MediaType.APPLICATION_JSON)
  public Response logout(@HeaderParam(HeaderParameters.KUDI_TOKEN) String sessionToken) throws JSONException, IllegalArgumentException {
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
      logoutResponse.setCode(StatusCodes.SUCCESS);
      logoutResponse.setStatus(200);
      logoutResponse.setResult(null);
      return Response.status(200).entity(logoutResponse).build();
    }

    //set all the parameters for an unsuccessful logout
    logoutResponse.setMessage("Logout failed");
    logoutResponse.setCode(StatusCodes.SYSTEM_ERROR);
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
    ratesResponse.setCode(StatusCodes.SUCCESS);
    ratesResponse.setStatus(200);
    ratesResponse.setResult(ratesResult);

    return Response.status(200).entity(ratesResponse).build();
  }

  @POST
  @Path("/accounts")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAccount(Account account) throws JSONException, IllegalArgumentException {

    //create a response and result object
    createAccountResponse = new KudiResponse();
    singleAccountResult = new SingleAccountResult();
    accName = account.getAccountName();
    singleAccountResult.setAccountName(accName);
    singleAccountResult.setAccountNumber(967);
    singleAccountResult.setCurrentBalance(0);
    singleAccountResult.setPreviousBalance(0);
    if(account.getTypeId() == AccountTypeIds.KUDI){
      singleAccountResult.setTypeId(AccountTypeIds.KUDI);
      accTypeId = AccountTypeIds.KUDI;
    }else if(account.getTypeId() == AccountTypeIds.SAVINGS){
      singleAccountResult.setTypeId(AccountTypeIds.SAVINGS);
      accTypeId = AccountTypeIds.SAVINGS;
    }else if(account.getTypeId() == AccountTypeIds.CURRENT){
      singleAccountResult.setTypeId(AccountTypeIds.CURRENT);
      accTypeId = AccountTypeIds.CURRENT;
    }

    singleAccountResult.setLastTransaction(Transactions.paymentTransactions[0]);
    singleAccountResult.setMetaData(null);

    createAccountResponse.setCode(StatusCodes.SUCCESS);
    createAccountResponse.setStatus(200);
    createAccountResponse.setMessage(account.getAccountName() + "Account Details");
    createAccountResponse.setResult(singleAccountResult);

    return Response.status(200).entity(createAccountResponse).build();
  }

  @GET
  @Path("/accounts/{accountNumber}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response viewAccount(@PathParam("accountNumber") int accountNumber) throws JSONException, IllegalArgumentException {
    KudiResponse viewAccountResponse = new KudiResponse();

    singleAccountResult = new SingleAccountResult();
    singleAccountResult.setAccountName("Tom's fake account");
    singleAccountResult.setAccountNumber(967);
    singleAccountResult.setTypeId(200);
    singleAccountResult.setCurrentBalance(0);
    singleAccountResult.setPreviousBalance(0);

    viewAccountResponse.setCode(StatusCodes.SUCCESS);
    viewAccountResponse.setStatus(200);
    viewAccountResponse.setMessage(accName + "Account Details");
    viewAccountResponse.setResult(singleAccountResult);

    return Response.status(200).entity(viewAccountResponse).build();
  }

  @GET
  @Path("/accounts")
  @Produces(MediaType.APPLICATION_JSON)
  public Response viewAccounts(@QueryParam("accountTypeId") int accountTypeId,
                               @QueryParam("minimumBalance") double minimumBalance) throws JSONException
  {
    AccountListResponse accountListResponse = new AccountListResponse();

    if(accountTypeId==100 && minimumBalance >=0) {
      BasicAccountInfoResult firstAccount = new BasicAccountInfoResult();
      firstAccount.setAccountName("One fake account");
      firstAccount.setAccountNumber(578);
      firstAccount.setTypeId(100);
      firstAccount.setCurrentBalance(100);
      firstAccount.setPreviousBalance(0);

      BasicAccountInfoResult secondAccount = new BasicAccountInfoResult();
      secondAccount.setAccountName("Second fake account");
      secondAccount.setAccountNumber(234);
      secondAccount.setTypeId(100);
      secondAccount.setCurrentBalance(500);
      secondAccount.setPreviousBalance(0);

      List<BasicAccountInfoResult> manyAccounts = new ArrayList<BasicAccountInfoResult>();
      manyAccounts.add(firstAccount);
      manyAccounts.add(secondAccount);

      accountListResponse.setStatus(200);
      accountListResponse.setMessage("Your accounts after the query");
      accountListResponse.setResult(manyAccounts);
      return Response.status(200).entity(accountListResponse).build();
    }

    return Response.status(400).build();
  }

  /**
   * Create a payment transaction
   */
  @POST
  @Path("/transactions/payment")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTransaction(PaymentTransaction transaction) throws JSONException{

    KudiResponse transactionResponse = new KudiResponse();
    PaymentTransactionResult transactionResult = new PaymentTransactionResult();

    try{
      if(transaction.getAccountFrom()==020254533344){
        transactionResult.setTransactionId(1);
        transactionResult.setReferenceCode(transaction.getReferenceCode());
        transactionResult.setAccountFrom(transaction.getAccountFrom());
        transactionResult.setAmount(transaction.getAmount());
        transactionResult.setState("Completed");
        transactionResult.setStartedAt(12);
      }
    }catch (Exception e){
      e.printStackTrace();
    }

    transactionResponse.setCode(StatusCodes.SUCCESS);
    transactionResponse.setMessage("Successful");
    transactionResponse.setStatus(0);
    transactionResponse.setResult(transactionResult);
    return Response.status(200).entity(transactionResponse).build();
  }

  /**
   * Create a simple transaction
   */
  @POST
  @Path("/transactions/simple")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTransaction(SimpleTransaction transaction) throws JSONException{

    KudiResponse transactionResponse = new KudiResponse();
    SimpleTransactionResult transactionResult = new SimpleTransactionResult();

    try{
      if(transaction.getAccountFrom()==020254533344){
        transactionResult.setTransactionId(2);
        transactionResult.setReferenceCode(transaction.getReferenceCode());
        transactionResult.setAccountFrom(transaction.getAccountFrom());
        transactionResult.setAccountTo(transaction.getAccountTo());
        transactionResult.setAmount(transaction.getAmount());
        transactionResult.setStartedAt(12);
        transactionResult.setState("Completed");
      }
    }catch (Exception e){
      e.printStackTrace();
    }

    transactionResponse.setCode(StatusCodes.SUCCESS);
    transactionResponse.setMessage("Successful");
    transactionResponse.setStatus(0);
    transactionResponse.setResult(transactionResult);
    return Response.status(200).entity(transactionResponse).build();
  }

  /**
   * Create a batch transaction
   */
  @POST
  @Path("/transactions/batch")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTransaction(BatchTransaction transaction) throws JSONException{

    KudiResponse transactionResponse = new KudiResponse();
    PaymentTransactionResult transactionResult = new PaymentTransactionResult();

    try{
      if(transaction.getAccountFrom()==020254533344){
        transactionResult.setTransactionId(1);
        transactionResult.setReferenceCode(transaction.getReferenceCode());
        transactionResult.setAccountFrom(transaction.getAccountFrom());
        transactionResult.setAmount(transaction.getAmount());
        transactionResult.setState("e");
        transactionResult.setStartedAt(12);
      }
    }catch (Exception e){
      e.printStackTrace();
    }

    transactionResponse.setCode(StatusCodes.SUCCESS);
    transactionResponse.setMessage("Successful");
    transactionResponse.setStatus(0);
    transactionResponse.setResult(transactionResult);
    return Response.status(200).entity(transactionResponse).build();
  }

  /**
   * View a single transaction.
   */
  @GET
  @Path("/transactions/{transactionId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response viewTransaction(@PathParam("transactionId") int id) throws JSONException{
    KudiResponse transactionResponse = new KudiResponse();
    SingleTransactionResult transactionResult = new SingleTransactionResult();


      if(id ==1) {
        transactionResult.setTransactionId(id);
        transactionResult.setReferenceCode(1023023123);
        transactionResult.setAccountFrom(020323112322);
        transactionResult.setAmount(1000000);
        transactionResult.setState("Completed");
        transactionResult.setStartedAt(12);
        transactionResult.setCommission(50);
        transactionResult.setCompletedAt(06);
        transactionResult.setTax(2);
        transactionResult.setComment("Thank You");
        transactionResult.setNote("Please dont disappoint");
        transactionResult.setEvent("Credit");
        transactionResult.setType("Payment");
      }


    transactionResponse.setCode(StatusCodes.SUCCESS);
    transactionResponse.setMessage("Successful");
    transactionResponse.setStatus(0);
    transactionResponse.setResult(transactionResult);
    return Response.status(200).entity(transactionResponse).build();
  }


  /**
   *  View multiple transactions.
   */
  @POST
  @Path("/transactions/multiple")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response viewMultipleTransaction(SimpleTransaction simpleTransaction){

    KudiResponse transactionResponse = new KudiResponse();
    CreateTransactionResult transactionResult = new CreateTransactionResult();

    try{
      if(simpleTransaction.getTransactionId()==1){
        simpleTransaction.setTransactionId(1);
        simpleTransaction.setReferenceCode(43);
        simpleTransaction.setAccountFrom(23);
        simpleTransaction.setAmount(100);
        simpleTransaction.setCommission(3);
        simpleTransaction.setTax(3);
        simpleTransaction.setRealAmount(3);
        simpleTransaction.setEvent("e");
        simpleTransaction.setState("e");
        simpleTransaction.setStartedAt(12);
        simpleTransaction.setCompletedAt(3);
        simpleTransaction.setType("w");
        simpleTransaction.setComment("d");
        simpleTransaction.setNote("s");
      }
      else if(simpleTransaction.getTransactionId()==2){
        simpleTransaction.setTransactionId(2);
        simpleTransaction.setReferenceCode(33);
        simpleTransaction.setAccountFrom(45);
        simpleTransaction.setAmount(500);
        simpleTransaction.setCommission(7);
        simpleTransaction.setTax(1);
        simpleTransaction.setRealAmount(9);
        simpleTransaction.setEvent("sf");
        simpleTransaction.setState("we");
        simpleTransaction.setStartedAt(10);
        simpleTransaction.setCompletedAt(9);
        simpleTransaction.setType("ew");
        simpleTransaction.setComment("tr");
        simpleTransaction.setNote("t3");
      }

    }catch (Exception e){
      e.printStackTrace();
    }

    transactionResponse.setCode(StatusCodes.SUCCESS);
    transactionResponse.setMessage("Successful");
    transactionResponse.setStatus(0);
    transactionResponse.setResult(transactionResult);

    return Response.status(200).entity(transactionResponse).build();
  }
}