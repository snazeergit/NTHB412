package com.nt.entity;

import org.hibernate.annotations.Proxy;

import com.nt.proxy.IMovie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIE_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = true, proxyClass = com.nt.proxy.IMovie.class)
public final class Movie implements IMovie {

	@Id
	@Column(name = "MID")
	private Integer mId;

	@Column(name = "MNAME", length = 20)
	private String mName;

	@Column(name = "HERO", length = 20)
	private String hero;

	@Column(name = "BUDGET")
	private Float budget;

}
