package com.springboot.restful.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restful.survey.model.Question;
import com.springboot.restful.survey.model.Survey;
import com.springboot.restful.survey.serviceImpl.QuestionServiceImpl;
import com.springboot.restful.survey.serviceImpl.SurveyServiceImpl;

@RestController
public class SurveyController {

	
	@Autowired
	private SurveyServiceImpl surveyServiceImpl;
	
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	
	
	@GetMapping("surveys")
	public List<Survey> getSurveyList() {
				
		List<Survey> list= surveyServiceImpl.getSurveyList();
    	if (list!= null && list.size()>0) {
    		return list;
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
    	}
	}
	
	
	@GetMapping("surveys/{surveyId}")
	public ResponseEntity<Survey> getSurveyById(@PathVariable Long surveyId) {
				
		return new ResponseEntity<Survey> (surveyServiceImpl.getSurveyById(surveyId), HttpStatus.OK);
	}
	
	@PostMapping("surveys")
	public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey) {
		
		return new ResponseEntity<Survey> (surveyServiceImpl.saveSurvey(survey), HttpStatus.CREATED);
	} 
	
	
	@PutMapping("surveys/{surveyId}")
	public ResponseEntity<Survey> updateSurvey(@PathVariable Long surveyId, @RequestBody Survey survey) {
		
		return new ResponseEntity<Survey> (surveyServiceImpl.updateSurvey(surveyId, survey), HttpStatus.OK);
	}
	
	
	@DeleteMapping("surveys/{surveyId}")
	public ResponseEntity<String> deleteSurvey(@PathVariable Long surveyId) {
		surveyServiceImpl.deleteSurvey(surveyId);
		return new ResponseEntity<String> ("Survey deleted successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping("question/{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
		questionServiceImpl.deleteQuestion(questionId);
		return new ResponseEntity<String> ("Question deleted successfully!", HttpStatus.OK);
	}
	
	
	@PostMapping("question/{surveyId}")
	public ResponseEntity<Question> saveQuestion(@PathVariable Long surveyId, @RequestBody Question question) {
		
		return new ResponseEntity<Question> (questionServiceImpl.saveQuestion(surveyId, question), HttpStatus.CREATED);
	} 

}
