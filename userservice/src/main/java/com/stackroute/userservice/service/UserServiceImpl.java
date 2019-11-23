package com.stackroute.userservice.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserConflictException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final transient UserRepository userRepository;

	/**
	 * Initializing the repository
	 *
	 * @param userRepository
	 */
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 *
	 * @param user - object need to be stored
	 * @return false - failure true - success
	 * @throws UserConflictException
	 */
	@Override
	public boolean saveUser(User user) throws UserConflictException, Exception {
		final Optional<User> optional = userRepository.findById(user.getUserId());
		if (optional.isPresent()) {
			throw new UserConflictException("Username already exist");
		}
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		if (constraintViolations.size() > 0) {
			System.out.println("VIOLATION OCCURED");
			for (ConstraintViolation<User> contraints : constraintViolations) {
				throw new Exception(contraints.getPropertyPath() + " " + contraints.getMessage());
			}
		} else {
			userRepository.save(user);
		}
		return true;
	}

	/**
	 * Desc: To get the user from database for given userId and password
	 *
	 * @param userId   - user id
	 * @param password - password
	 * @return false - failure true - success
	 * @throws UserNotFoundException
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password) {
		final User user = userRepository.findByUserIdAndPassword(userId, password);
		return user;
	}
}
