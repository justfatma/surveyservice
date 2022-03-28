package com.springboot.restful.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restful.survey.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	
	List<Question> findBySurveyId(Long surveyId);
}
