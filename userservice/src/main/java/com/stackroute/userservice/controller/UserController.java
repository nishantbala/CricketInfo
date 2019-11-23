package com.stackroute.userservice.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserConflictException;
import com.stackroute.userservice.service.JwtToken;
import com.stackroute.userservice.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtToken TokenGenerator;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Desc: To register the user in Match application
	 *
	 * @param user
	 * @return json response
	 */
	@PostMapping(value = "/register", produces = { "application/json" })
	public ResponseEntity<?> registerUser(@RequestBody final User user) {
		ResponseEntity<?> entity = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			userService.saveUser(user);
			map.put("result", "success");
			map.put("message", "Registered Successfully");
			entity = new ResponseEntity<Map<Object, Object>>(map, HttpStatus.CREATED);
		} catch (UserConflictException e) {
			map.put("result", "failed");
			map.put("message", e.getMessage());
			entity = new ResponseEntity<Map<Object, Object>>(map, HttpStatus.CONFLICT);
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("message", e.getMessage());
			entity = new ResponseEntity<Map<Object, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/**
	 * Desc: User login service
	 *
	 * @param user
	 * @return json response
	 */
	@PostMapping(path = "/login", produces = { "application/json" })
	public ResponseEntity<?> loginUser(@RequestBody final User userInfo) {
		ResponseEntity<?> entity = null;

		try {
			String userName = userInfo.getUserId();
			String password = userInfo.getPassword();

			if (userName == null || password == null) {
				throw new Exception("Username and Password is required");
			}

			User user = userService.findByUserIdAndPassword(userName, password);
			if (null == user) {
				throw new Exception("Invalid Login Information");
			}
			String jwtToken = TokenGenerator.generateToken(user);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("userId",user.getUserId());
			map.put("result", "success");
			map.put("firstName", user.getFirstName());
			map.put("lastName", user.getLastName());
			map.put("isAdmin",user.getIsAdmin());
			map.put("token", jwtToken);
			map.put("message", "Successfully loggedin");
			return new ResponseEntity<Map<Object, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>("{ \"result\" : \"failed\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
		return entity;
	}
}

