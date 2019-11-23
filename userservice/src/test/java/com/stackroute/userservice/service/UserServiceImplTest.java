package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserConflictException;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
  @Mock
  private transient UserRepository userRepo;

  private transient User user;

  @InjectMocks
  private transient UserServiceImpl userServiceImpl;

  transient Optional<User> options;

  @Before
  public void setupMock() {
    MockitoAnnotations.initMocks(this);
    this.user = new User("poovi", "poovi", "poovalagan", "thana", false, new Date());
    options = Optional.of(user);
  }

  @Test
  public void testMockCreation() {
    assertNotNull("JPA Repository injection fails", userRepo);
  }

  @Test
  public void testRegisterationSuccess() throws UserConflictException {
    when(userRepo.save(user)).thenReturn(user);

    boolean isSave;
	try {
		isSave = userServiceImpl.saveUser(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		isSave = false;
		e.printStackTrace();
	}
    assertEquals("Registered", true, isSave);
    verify(userRepo, times(1)).save(user);
  }

  @Test(expected = UserConflictException.class)
  public void testRegisterationFailure() throws UserConflictException {
    when(userRepo.findById(user.getUserId())).thenReturn(options);
    when(userRepo.save(user)).thenReturn(user);
    boolean isSave;
	try {
		isSave = userServiceImpl.saveUser(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		isSave = false;
		e.printStackTrace();
	}
    assertEquals("Registered", false, isSave);
  }

  @Test
  public void testLoginSuccess() throws UserConflictException {
    when(userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
    User userResult = userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword());
    assertNotNull(userResult);
    when(userRepo.save(user)).thenReturn(user);
    verify(userRepo, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
  }

  @Test
  public void testLoginFailure() throws UserConflictException {
    when(userRepo.findById("superhero")).thenReturn(null);
    User userResult = userRepo.findByUserIdAndPassword("jana", "jana");
    assertNull(userResult);
  }
}
