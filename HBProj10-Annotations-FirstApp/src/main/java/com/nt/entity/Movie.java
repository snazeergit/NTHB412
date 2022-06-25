package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "MOVIE_INFO")
@Data
@AllArgsConstructor
public class Movie {

	@Id
	@Column(name = "MID")
	private Integer mId;

	@Column(name = "MNAME")
	private String mName;

	@Column(name = "HERO")
	private String hero;

	@Column(name = "BUDGET")
	private Float budget;

}
