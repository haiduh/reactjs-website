package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Feedback {

	@Id
	@Column(length = 36, name ="id")
    private String id;
	@Column(name = "rating")
	private Integer rating;

	@Column
	private String opinion;

	@Column
	private String category;

	 public Feedback() {
	    }

 public Feedback(String id,  Integer rating, String opinion, String category) {
	        this.id = id;
	        this.rating = rating;
			this.opinion= opinion;
			this.category= category;
	    }

    public String getId() {
		 return id;
    }

    public void setId(String id) {
		 this.id = id;
    }

	public Integer getRating() {
		 return rating;
	}
	public void setRating(Integer rating) {
		 this.rating = rating;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}