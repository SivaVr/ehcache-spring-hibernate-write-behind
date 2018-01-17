package com.sivavr.ehcache.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "super_heros")
public class SuperHero implements Serializable {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "hero_name", unique = true, nullable = false)
	private String name;
	@Column(name = "movie", nullable = false)
	private String movie;
	@Column(name = "rank", nullable = false)
	private int rank;
	@Column(name = "hero_description")
	private String description;
	@Column(name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
