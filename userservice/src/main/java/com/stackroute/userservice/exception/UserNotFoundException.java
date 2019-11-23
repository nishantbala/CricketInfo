package com.stackroute.userservice.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception  {
  private String message;
  /**
   * Constructor
   *
   * @param message
   */
  public UserNotFoundException(final String message) {
    super();
    this.message = message;
  }
}
