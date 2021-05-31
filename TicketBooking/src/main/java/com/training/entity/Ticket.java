package com.training.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ticketId;
	String ticketNumber;

	@Column(columnDefinition = "DATE")
	LocalDate dateOfJourney;
	@Column(columnDefinition = "DATE")
	LocalDate dateOfBooking;

	String fromLocation;

	String toLocation;
	Double fare;

	int userId;
	int trainId;

	int numberOfSeats;

	@OneToMany(cascade = { CascadeType.ALL })
	List<Passengers> passengers;

}
