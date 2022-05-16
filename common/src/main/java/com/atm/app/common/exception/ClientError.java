package com.atm.app.common.exception;

public abstract class ClientError extends Exception {
  private int code;

  public ClientError(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
