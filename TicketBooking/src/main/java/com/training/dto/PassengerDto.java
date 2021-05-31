package com.training.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDto {

	@NotEmpty(message = "Passenger name is required")
	String name;
	@NotEmpty(message = "Passenger age is required")
	String age;
	String seatNumber;
}
