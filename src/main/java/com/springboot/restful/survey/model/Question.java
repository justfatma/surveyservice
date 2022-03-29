package com.springboot.restful.survey.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String answerE;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Survey survey;
	
	public Question() {
		super();
	}
	
	public Question(String description,String answerA,String answerB,String answerC,String answerD,String answerE) {
		super();
		this.description=description;
		this.answerA=answerA;
		this.answerB=answerB;
		this.answerC=answerC;
		this.answerD=answerD;
		this.answerE=answerE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public String getAnswerE() {
		return answerE;
	}

	public void setAnswerE(String answerE) {
		this.answerE = answerE;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", answerA=" + answerA + ", answerB=" + answerB
				+ ", answerC=" + answerC + ", answerD=" + answerD + ", answerE=" + answerE + ", survey=" + survey + "]";
	}
	
}
