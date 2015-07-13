/*
 * Copyright (c) 2015 "Rancard Solutions"
 */

package com.rancard.kudi.client.results;

import lombok.Data;

import java.util.HashMap;

/**
 * Represents a user's exchange result rates result on the KUDI platform.
 */

@Data
public class ExchangeRatesResult implements KudiResult{

  private HashMap<String, Double> rates;
}
