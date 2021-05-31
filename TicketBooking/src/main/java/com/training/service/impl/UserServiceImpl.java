package com.training.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.dto.UserDto;
import com.training.entity.User;
import com.training.repository.UserRepository;
import com.training.service.UserService;

/**
 * @author payal.parate
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	/**
	 *
	 */
	@Override
	public UserDto saveUser(@Valid UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, userDto);
		return userDto;
	}

	/**
	 *
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 *
	 */
	@Override
	public UserDto getUserById(int userId) {
		UserDto userDto = new UserDto();
		User user = userRepository.findByUserId(userId);
		BeanUtils.copyProperties(user, userDto);
		return userDto;

	}

	/**
	 *
	 */
	@Override
	public User updateUserInfo(int userId, User user) {
		UserDto userDto = getUserById(userId);
		userDto.setPassword(user.getPassword());
		userDto.setPhoneNo(user.getPhoneNo());
		userDto.setAge(user.getAge());
		User user2 = new User();
		BeanUtils.copyProperties(userDto, user2);
		return userRepository.save(user2);
	}

	/**
	 *
	 */
	@Override
	public void deleteUserInfoById(int userId) {
		userRepository.deleteById(userId);

	}

	/**
	 *
	 */
	@Override
	public User loginUser(String username, String password) {

		return userRepository.findByUserNameAndPassword(username, password);
	}

}
