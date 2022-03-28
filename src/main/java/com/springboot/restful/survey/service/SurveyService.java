package com.springboot.restful.survey.service;

import java.util.List;

import com.springboot.restful.survey.model.Survey;

public interface SurveyService {
	
	public Survey getSurveyById(Long surveyId);
	public List<Survey> getSurveyList();
	public Survey saveSurvey(Survey survey);
	public Survey updateSurvey(Long id, Survey survey);
	public void deleteSurvey(Long surveyId);

}
