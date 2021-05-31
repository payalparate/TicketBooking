package com.training.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.TrainRequestDto;
import com.training.dto.TrainResponseDto;
import com.training.entity.Train;
import com.training.exception.MessageResponse;
import com.training.exception.TrainNotFoundException;
import com.training.service.TrainService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/Trains")
public class TrainController {
	static Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	TrainService trainService;

	/**
	 * @param trainDto
	 * @return
	 * @throws MethodArgumentNotValidException
	 */
	@PostMapping()
	public ResponseEntity<MessageResponse> saveTrainDetails(@Valid @RequestBody TrainRequestDto trainDto)
			throws MethodArgumentNotValidException {
		TrainResponseDto dto = trainService.saveTrainDetails(trainDto);
		System.out.println("dto" + dto);
		if (dto != null) {
			logger.info("Train details are saved in database");
			return ResponseEntity.ok(new MessageResponse("Your train is registered successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
	}

	/**
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 * @throws MethodArgumentNotValidException
	 * @throws ParseException
	 */
	@GetMapping()
	public ResponseEntity<?> getBySourceDestinationAndDate(@Valid @RequestParam String source,
			@Valid @RequestParam String destination, @RequestParam String date)
			throws MethodArgumentNotValidException, ParseException {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
		List<Train> train = trainService.getBySourceDestinationAndDate(source, destination, localDate);
		if (train != null) {
			List<Train> trains = new ArrayList<>();
			for (Train train2 : train) {
				trains.add(train2);
			}

			logger.info("User Trying to fetch train details by source, destination and date ");
			return new ResponseEntity<>(trains, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));

	}

	/**
	 * @param trainId
	 * @return
	 * @throws MethodArgumentNotValidException
	 * @throws TrainNotFoundException
	 */
	@GetMapping("/{trainId}")
	public ResponseEntity<?> getTrainByTrainId(@PathVariable int trainId)
			throws MethodArgumentNotValidException, TrainNotFoundException {
		TrainResponseDto trainResponseDto = trainService.getTrainByTrainId(trainId);
		if (trainResponseDto != null) {
			logger.info("Requested train found !!!");
			return new ResponseEntity<TrainResponseDto>(trainResponseDto, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid train id"));
	}
}
