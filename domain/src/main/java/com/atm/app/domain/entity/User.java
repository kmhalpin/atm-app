/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.atm.app.domain.entity;

public class User {
  private String accountNumber;
  private int securityPin;
  private String name;
  private long balance;
  private long createdAt;
  private long updatedAt;

  public User() {
  }

  public User(
      String accountNumber,
      int securityPin,
      String name,
      long balance,
      long createdAt,
      long updatedAt) {
    this.accountNumber = accountNumber;
    this.securityPin = securityPin;
    this.name = name;
    this.balance = balance;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public int getSecurityPin() {
    return securityPin;
  }

  public void setSecurityPin(int securityPin) {
    this.securityPin = securityPin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
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
