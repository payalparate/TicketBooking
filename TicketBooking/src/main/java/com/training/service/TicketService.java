package com.training.service;

import java.util.List;

import javax.validation.Valid;

import com.training.dto.TicketRequestDto;
import com.training.dto.TicketResponseDto;
import com.training.entity.Ticket;
import com.training.exception.TrainNotFoundException;
import com.training.exception.UserNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface TicketService {

	/**
	 * @param ticketDto
	 * @return
	 * @throws UserNotFoundException
	 * @throws TrainNotFoundException
	 */
	TicketResponseDto saveTicketDetails(@Valid TicketRequestDto ticketDto)
			throws UserNotFoundException, TrainNotFoundException;

	/**
	 * @param ticketId
	 * @return
	 */
	String deleteByTicketId(int ticketId);

	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	List<Ticket> getTicketDetailsByUserId(int userId) throws UserNotFoundException;

}
