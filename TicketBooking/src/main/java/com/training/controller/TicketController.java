package com.training.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.TicketRequestDto;
import com.training.dto.TicketResponseDto;
import com.training.entity.Ticket;
import com.training.exception.MessageResponse;
import com.training.exception.TrainNotFoundException;
import com.training.exception.UserNotFoundException;
import com.training.service.TicketService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/Tickets")
public class TicketController {

	/**
	 * 
	 */
	@Autowired
	TicketService ticketService;

	/**
	 * 
	 */
	static Logger logger = LoggerFactory.getLogger(TicketController.class);

	/**
	 * @param ticketDto
	 * @return
	 * @throws MethodArgumentNotValidException
	 * @throws UserNotFoundException
	 * @throws TrainNotFoundException
	 */
	@PostMapping()
	public ResponseEntity<?> bookTickets(@Valid @RequestBody TicketRequestDto ticketDto)
			throws MethodArgumentNotValidException, UserNotFoundException, TrainNotFoundException {
		TicketResponseDto dto = ticketService.saveTicketDetails(ticketDto);
		if (dto != null) {
			logger.info("Tickets are booked successfully");
			return ResponseEntity.ok(new MessageResponse("Your ticket is booked successfully"));
		}
		logger.info("Unable to book ticket due to technical issue");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
	}

	/**
	 * @param ticketId
	 * @return
	 */
	@DeleteMapping("/{ticketId}")
	public String deleteByTicketId(@PathVariable int ticketId) {
		ticketService.deleteByTicketId(ticketId);
		logger.info("Entry deleted successfully");
		return "Deleted successfully";
	}

	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getTicketDetailsByUserId(@PathVariable int userId) throws UserNotFoundException {
		List<Ticket> tickets = ticketService.getTicketDetailsByUserId(userId);
		if (tickets != null) {
			List<Ticket> tickets1 = new ArrayList<>();
			for (Ticket ticket : tickets) {
				tickets1.add(ticket);

			}

			logger.info("User Trying to fetch ticket by userId");
			return new ResponseEntity<>(tickets1, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
	}

}
