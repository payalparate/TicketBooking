package com.training.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.dto.StationDto;
import com.training.dto.TrainRequestDto;
import com.training.dto.TrainResponseDto;
import com.training.entity.Station;
import com.training.entity.Train;
import com.training.exception.TrainNotFoundException;
import com.training.repository.TrainRepository;
import com.training.service.TrainService;

/**
 * @author payal.parate
 *
 */
@Service
@Transactional
public class TrainServiceImpl implements TrainService {

	@Autowired
	TrainRepository trainRepository;
	static Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

	/**
	 *
	 */
	@Override
	public TrainResponseDto saveTrainDetails(@Valid TrainRequestDto trainDto) {
		Train train = new Train();
		Station station = new Station();
		List<StationDto> StationDtoList = trainDto.getStation();
		List<Station> stationList = new ArrayList<>();

		for (StationDto StationDto : StationDtoList) {
			BeanUtils.copyProperties(StationDto, station);
			stationList.add(station);
		}

		BeanUtils.copyProperties(trainDto, train);
		train.setStation(stationList);
		TrainResponseDto dto = new TrainResponseDto();
		Train savedTrain = trainRepository.save(train);
		logger.info("Train details are saved successfully in the database");
		BeanUtils.copyProperties(savedTrain, dto);
		return dto;
	}

	/**
	 *
	 */
	@Override
	public List<Train> getBySourceDestinationAndDate(String source, String destination, LocalDate date) {
		logger.info("Trying to fetch Train details by date");
		return trainRepository.findBySourceContainsAndDestinationContainsAndDate(source, destination, date);

	}

	/**
	 *
	 */
	@Override
	public TrainResponseDto getTrainByTrainId(int trainId) throws TrainNotFoundException {
		TrainResponseDto trainResponseDto = new TrainResponseDto();
		if (trainRepository.findById(trainId).isPresent()) {
			Train train = trainRepository.findById(trainId).get();
			BeanUtils.copyProperties(train, trainResponseDto);
			return trainResponseDto;
		} else {
			throw new TrainNotFoundException("Train not found for specified id :");

		}
	}

	/**
	 *
	 */
	@Override
	public void updateTrainDetails(int trainId, Train train) {
		Train train2 = trainRepository.findById(trainId).get();
		if (train != null) {
			train2.setSource(train.getSource());
			train2.setDestination(train.getDestination());
			train2.setDate(train.getDate());
			train2.setStation(train.getStation());
			train2.setAvaiableSeats(train.getAvaiableSeats());
			trainRepository.save(train2);
		}

	}

}
