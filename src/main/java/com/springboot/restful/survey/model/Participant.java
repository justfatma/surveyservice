package com.springboot.restful.survey.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Participant {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	private String age;
	
	@OneToMany(mappedBy="participant")
	private List<ParticipantAnswer> answerList;
	
	public Participant() {
		super();
	}
	
	public Participant(String name, String surname, String age, List<ParticipantAnswer> answerList) {
		super();
		this.name=name;
		this.surname=surname;
		this.age=age;
		this.answerList=answerList;
	}
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public List<ParticipantAnswer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<ParticipantAnswer> answerList) {
		this.answerList = answerList;
	}
	
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", answerList="
				+ answerList + "]";
	}
	

}
