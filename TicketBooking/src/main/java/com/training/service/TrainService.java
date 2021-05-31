package com.training.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.training.dto.TrainRequestDto;
import com.training.dto.TrainResponseDto;
import com.training.entity.Train;
import com.training.exception.TrainNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface TrainService {
	/**
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	public List<Train> getBySourceDestinationAndDate(String source, String destination, LocalDate date);

	/**
	 * @param trainDto
	 * @return
	 */
	public TrainResponseDto saveTrainDetails(@Valid TrainRequestDto trainDto);

	// public List<Train> getBySourceDestinationAndDays(String source, String
	// destination, String[] days);

	/**
	 * @param trainId
	 * @return
	 * @throws TrainNotFoundException
	 */
	public TrainResponseDto getTrainByTrainId(int trainId) throws TrainNotFoundException;

	/**
	 * @param trainId
	 * @param trainDto
	 */
	public void updateTrainDetails(int trainId, Train train);

}
