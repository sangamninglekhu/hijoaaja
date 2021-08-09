package com.chumlung.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gallery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	private String image;
	private int status;
	
	@ManyToOne
	@JoinColumn(name="news_id")
	private News news_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public News getNews_id() {
		return news_id;
	}
	public void setNews_id(News news_id) {
		this.news_id = news_id;
	}
	
	
}
