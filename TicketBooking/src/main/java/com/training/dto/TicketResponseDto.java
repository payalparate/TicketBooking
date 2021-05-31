package com.training.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import com.training.entity.Passengers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDto {
	int ticketId;
	String ticketNumber;

	@FutureOrPresent
	LocalDate dateOfJourney;
	LocalDate dateOfBooking;

	@NotEmpty(message = "source should be non empty")
	String fromLocation;

	@NotEmpty(message = "destination should be non empty")
	String toLocation;
	Double fare;

	int userId;
	int trainId;

	int numberOfSeats;
	List<Passengers> passengers;

}
