package com.springboot.restful.survey.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Survey {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy="survey")
	private List<Question> questionList;
	
	public Survey() {
		super();
	}
	
	public Survey(String name, String description, List<Question> questionList) {
		super();
		this.name=name;
		this.description=description;
		this.questionList=questionList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", name=" + name + ", description=" + description + ", questionList=" + questionList
				+ "]";
	}

}
