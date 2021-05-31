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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "train")
@Getter
@Setter
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int trainId;

	private String trainNumber;
	@OneToMany(targetEntity = Station.class, cascade = CascadeType.ALL)
	private List<Station> station;

	private String source;

	private String destination;

	private int avaiableSeats;
	int duration;

	@Column(columnDefinition = "DATE")
	private LocalDate date;

}
