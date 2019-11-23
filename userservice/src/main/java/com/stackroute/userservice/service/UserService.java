package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserConflictException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {
  /**
   * Desc: To store the user in database
   *
   * @param user - object need to be stored
   * @return false - failure true - success
   * @throws UserConflictException
 * @throws Exception 
   *
   */
  boolean saveUser(User user) throws UserConflictException, Exception;

  /**
   * Desc: To get the user from database for given userId and password
   *
   * @param userId - User id
   * @param password - Password
   * @return false - failure true - success
   * @throws UserNotFoundException
   */
  User findByUserIdAndPassword(String userId, String password);
}
