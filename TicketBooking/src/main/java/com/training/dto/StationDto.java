package com.training.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDto {
	@NotEmpty(message = "Station name is required")
	private String stationName;
	@NotEmpty(message = "Station code is required")
	private String stationCode;
}
