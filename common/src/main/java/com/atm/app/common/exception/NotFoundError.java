package com.atm.app.common.exception;

public class NotFoundError extends ClientError {
  public NotFoundError(String message) {
    super(message, 404);
  }
}
