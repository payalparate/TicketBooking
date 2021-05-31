package com.training.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainRequestDto {
	private String trainNumber;
	private List<StationDto> station;

	@NotEmpty(message = "source is required")
	@Size(min = 2, max = 10)
	private String source;

	@NotEmpty(message = "Destination is required")
	@Size(min = 2, max = 10)
	private String destination;

	int duration;

	@FutureOrPresent
	private LocalDate date;

	public TrainRequestDto() {
		super();
	}

	public TrainRequestDto(List<StationDto> station, @NotEmpty(message = "source is required") String source,
			@NotEmpty(message = "Destination is required") String destination, int duration, LocalDate date) {
		super();

		this.station = station;
		this.source = source;
		this.destination = destination;
		this.duration = duration;
		this.date = date;
	}
}
