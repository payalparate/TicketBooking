package com.training.service;

import java.util.List;

import javax.validation.Valid;

import com.training.dto.UserDto;
import com.training.entity.User;

/**
 * @author payal.parate
 *
 */
public interface UserService {
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUser(String username, String password);

	/**
	 * @param userDto
	 * @return
	 */
	public UserDto saveUser(@Valid UserDto userDto);

	/**
	 * @return
	 */
	public List<User> getAllUsers();

	/**
	 * @param userId
	 * @return
	 */
	public UserDto getUserById(int userId);

	/**
	 * @param userId
	 * @param user
	 * @return
	 */
	public User updateUserInfo(int userId, User user);

	/**
	 * @param userId
	 */
	public void deleteUserInfoById(int userId);
}
