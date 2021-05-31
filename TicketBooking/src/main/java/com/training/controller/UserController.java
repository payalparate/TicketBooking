package com.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.UserDto;
import com.training.entity.User;
import com.training.exception.MessageResponse;
import com.training.service.UserService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/Users")
public class UserController {

	@Autowired
	UserService userService;

	static Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping("/login")
	public User loginUser(@Valid @RequestParam String username, @Valid @RequestParam String password) {
		logger.info("Logged in by User into system");
		return userService.loginUser(username, password);

	}

	/**
	 * @param userDto
	 * @return
	 * @throws MethodArgumentNotValidException
	 */
	@PostMapping()
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto) throws MethodArgumentNotValidException {
		UserDto dto = userService.saveUser(userDto);
		if (dto != null) {
			return ResponseEntity.ok(new MessageResponse("Train is successfully saved !!!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));

	}

	/**
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		logger.info("Trying to fetch all users in the system");
		return new ResponseEntity(users, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {
		UserDto userDto = userService.getUserById(userId);
		logger.info("Got user with id : " + userId);
		if (userDto != null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid userId"));
	}

	/**
	 * @param userId
	 * @param user
	 * @return
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<?> UpdateUserInfo(@PathVariable int userId, @RequestBody User user) {
		User u = userService.updateUserInfo(userId, user);
		if (u != null) {
			return ResponseEntity.ok(new MessageResponse("User is successfully saved !!!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));

	}

	/**
	 * @param userId
	 */
	@DeleteMapping("/{userId}")
	public void deleteUserInfo(@PathVariable int userId) {
		userService.deleteUserInfoById(userId);
	}

}
