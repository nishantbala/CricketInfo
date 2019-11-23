package com.stackroute.userservice.exception;

@SuppressWarnings("serial")
public class UserConflictException extends Throwable{
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Constructor
   *
   * @param message
   */
  public UserConflictException(final String message) {
    super();
    this.message = message;
  }

  @Override
  public String toString() {
    return "User Already Exist";
  }
}
