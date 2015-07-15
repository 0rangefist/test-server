/**
 * Copyright (c) 2015 "Rancard Solutions".
 */

package com.rancard.kudi.client.results;


import com.rancard.kudi.client.domain.BasicAccount;
import lombok.Data;

@Data
public class MultipleAccountResult implements KudiResult {
 private BasicAccount multipleAccounts[];
}
