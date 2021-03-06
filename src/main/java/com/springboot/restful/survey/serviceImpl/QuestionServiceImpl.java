package com.springboot.restful.survey.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.restful.survey.model.Question;
import com.springboot.restful.survey.model.Survey;
import com.springboot.restful.survey.repository.QuestionRepository;
import com.springboot.restful.survey.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public void deleteQuestion(Long questionId) {
		Question currentQuestion = questionRepository.findById(questionId).orElseThrow( 
				()-> new ResourceNotFoundException("QuestionServiceImpl/deleteQuestion : Question Id not found. -> " + questionId));
	
		questionRepository.delete(currentQuestion);
	}

	@Override
	public Question saveQuestion(Long surveyId, Question question) {
		
		Survey survey = new Survey();
		survey.setId(surveyId);
		question.setSurvey(survey);
		
		Question savedQuestion = questionRepository.save(question);
		return savedQuestion;
	}

}
