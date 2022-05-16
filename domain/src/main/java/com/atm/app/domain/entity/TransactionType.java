package com.atm.app.domain.entity;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
  DEPOSIT("DEPOSIT"),
  WITHDRAW("WITHDRAW"),
  TRANSFER("TRANSFER");

  private static final Map<String, TransactionType> BY_LABEL = new HashMap<>();

  static {
    for (TransactionType e : values()) {
      BY_LABEL.put(e.label, e);
    }
  }

  public final String label;

  private TransactionType(String l) {
    this.label = l;
  }

  public static TransactionType getTypeByLabel(String l) {
    return BY_LABEL.get(l);
  }
};