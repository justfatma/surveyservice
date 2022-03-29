package com.springboot.restful.survey.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParticipantAnswer {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Participant participant;
	
	@ManyToOne
	private Question question;
	
	@Enumerated(EnumType.STRING)
	private Answer answer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "ParticipantAnswer [id=" + id + ", participant=" + participant + ", question=" + question + ", answer="
				+ answer + "]";
	}
	
}
