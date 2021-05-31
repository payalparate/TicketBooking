package com.training.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.training.entity.Station;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainResponseDto {
	private int trainId;
	private String trainNumber;
	private List<Station> station;

	@NotEmpty(message = "source is required")
	@Size(min = 2, max = 10)
	private String source;

	@NotEmpty(message = "Destination is required")
	@Size(min = 2, max = 10)
	private String destination;

	int duration;
	int avaiableSeats;

	@FutureOrPresent
	private LocalDate date;

	public TrainResponseDto() {
		super();
	}

	public TrainResponseDto(List<Station> station, @NotEmpty(message = "source is required") String source,
			@NotEmpty(message = "Destination is required") String destination, int avaiableSeats, int duration,
			LocalDate date) {
		super();

		this.station = station;
		this.source = source;
		this.destination = destination;
		this.avaiableSeats = avaiableSeats;
		this.duration = duration;
		this.date = date;
	}
}
