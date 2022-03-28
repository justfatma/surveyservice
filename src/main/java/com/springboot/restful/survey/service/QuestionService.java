package com.springboot.restful.survey.service;

import com.springboot.restful.survey.model.Question;

public interface QuestionService {

	public Question saveQuestion(Long surveyId, Question question);
	public void deleteQuestion(Long questionId);
}
