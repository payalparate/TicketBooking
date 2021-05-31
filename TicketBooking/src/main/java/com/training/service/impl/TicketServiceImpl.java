package com.training.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.dto.PassengerDto;
import com.training.dto.TicketRequestDto;
import com.training.dto.TicketResponseDto;
import com.training.dto.TrainResponseDto;
import com.training.entity.Passengers;
import com.training.entity.Ticket;
import com.training.entity.Train;
import com.training.exception.TrainNotFoundException;
import com.training.exception.UserNotFoundException;
import com.training.repository.TicketRepository;
import com.training.service.TicketService;
import com.training.service.TrainService;
import com.training.service.UserService;

/**
 * @author payal.parate
 *
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TrainService trainService;

	@Autowired
	UserService userService;
	static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	/**
	 *
	 */
	@Override
	public TicketResponseDto saveTicketDetails(@Valid TicketRequestDto ticketDto)
			throws UserNotFoundException, TrainNotFoundException {
		TicketResponseDto ticketResponseDto = new TicketResponseDto();
		Ticket ticket = new Ticket();
		Passengers passengers = new Passengers();

		List<PassengerDto> passengerDtoList = ticketDto.getPassengers();
		List<Passengers> passengerList = new ArrayList<>();

		for (PassengerDto passengerDto : passengerDtoList) {
			BeanUtils.copyProperties(passengerDto, passengers);
			passengerList.add(passengers);

		}

		String ticketNumber = RandomStringUtils.randomAlphanumeric(10).toUpperCase();

		BeanUtils.copyProperties(ticketDto, ticket);
		ticket.setTicketNumber(ticketNumber);
		ticket.setPassengers(passengerList);

		LocalDate lt = LocalDate.now();
		ticket.setDateOfBooking(lt);
		ticket.setNumberOfSeats(ticketDto.getPassengers().size());
		TrainResponseDto trainResponseDto = trainService.getTrainByTrainId(ticket.getTrainId());
		Train train = new Train();
		if (ObjectUtils.isEmpty(trainResponseDto)) {
			logger.error("Unable to find train");
			throw new TrainNotFoundException("Unable to find train");

		}
		BeanUtils.copyProperties(trainResponseDto, train);
		if (userService.getUserById(ticket.getUserId()) == null) {
			logger.error("Unable to locate user");
			throw new UserNotFoundException("Unable to locate user");
		}
		if (ticket.getNumberOfSeats() <= train.getAvaiableSeats()) {
			train.setAvaiableSeats(train.getAvaiableSeats() - ticket.getNumberOfSeats());
			trainService.updateTrainDetails(ticket.getTrainId(), train);
			Ticket savedTicket = ticketRepository.save(ticket);

			BeanUtils.copyProperties(savedTicket, ticketDto);
			return ticketResponseDto;
		}
		return ticketResponseDto;

	}

	/**
	 *
	 */
	@Override
	public String deleteByTicketId(int ticketId) {
		ticketRepository.deleteById(ticketId);
		return "Deleted";
	}

	/**
	 *
	 */
	@Override
	public List<Ticket> getTicketDetailsByUserId(int userId) throws UserNotFoundException {
		if (userService.getUserById(userId) != null) {
			return ticketRepository.findByUserId(userId);
		} else
			throw new UserNotFoundException("Unable to locate user");

	}

}
