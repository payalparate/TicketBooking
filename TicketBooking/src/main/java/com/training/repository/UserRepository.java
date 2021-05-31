package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.User;

/**
 * @author payal.parate
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	User findByUserNameAndPassword(String username, String password);

	/**
	 * @param userId
	 * @return
	 */
	User findByUserId(int userId);

}
