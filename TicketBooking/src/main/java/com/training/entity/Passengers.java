package com.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passengers")
@Getter
@Setter
public class Passengers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int passengerId;
	String name;
	String age;
	String seatNumber;

}
