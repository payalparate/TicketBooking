package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Ticket;

/**
 * @author payal.parate
 *
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	/**
	 * @param userId
	 * @return
	 */
	List<Ticket> findByUserId(int userId);

}
