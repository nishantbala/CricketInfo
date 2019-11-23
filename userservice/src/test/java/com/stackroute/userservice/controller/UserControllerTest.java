package com.stackroute.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserConflictException;
import com.stackroute.userservice.service.JwtToken;
import com.stackroute.userservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private transient MockMvc mockMvc;

  @MockBean
  private transient UserService userService;

  @MockBean
  private JwtToken TokenGenerator;

  private transient User user;

  @Before
  public void setupMock() {
    MockitoAnnotations.initMocks(this);
    this.user = new User("poovi", "poovi", "poovalagan", "jaga", true, new Date());
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
		String json = ow.writeValueAsString(this.user);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  /**
   * @param dynamic json object
   * @return
   */
  private String jsonToString(final Object obj1) throws JsonProcessingException {
    String result;
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String message = mapper.writeValueAsString(obj1);
      result = message;
    } catch (JsonProcessingException e) {
      result = "Json processing error";
    }

    return result;
  }

  @Test
  public void testRegisterUser() throws Exception, UserConflictException {
    when(userService.saveUser(user)).thenReturn(true);
    mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(jsonToString(user)))
      .andExpect(status().isCreated())
      .andDo(print());

    verify(userService, times(1)).saveUser(Mockito.any(User.class));
    verifyNoMoreInteractions(userService);
  }

  @Test
  public void testLoginUser() throws Exception, UserConflictException {

    String userId = "poovi";
    String password = "poovi";

    when(userService.saveUser(user)).thenReturn(true);
    when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
    mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(user)))
      .andExpect(status().isOk());

    verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
    verifyNoMoreInteractions(userService);
  }

}
