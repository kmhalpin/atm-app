package com.atm.app.domain.entity;

public class Transaction {
  int id;
  String accountFrom;
  String accountTo;
  long balance;
  TransactionType type;
  long createdAt;
  long updatedAt;

  public Transaction(){};

  public Transaction(
      int id,
      String accountFrom,
      String accountTo,
      long balance,
      String type,
      long createdAt,
      long updatedAt) {
    this.id = id;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.balance = balance;
    this.type = TransactionType.getTypeByLabel(type);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }

}
