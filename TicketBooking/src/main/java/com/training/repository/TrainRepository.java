package com.training.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Train;

/**
 * @author payal.parate
 *
 */
public interface TrainRepository extends JpaRepository<Train, Integer> {

	/**
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	List<Train> findBySourceContainsAndDestinationContainsAndDate(String source, String destination, LocalDate date);

	// List<Train> findBySourceAndDestinationAndDays(String source, String
	// destination, String[] days);

	/**
	 * @param trainDto1
	 */
	void save(Optional<Train> trainDto1);

}
