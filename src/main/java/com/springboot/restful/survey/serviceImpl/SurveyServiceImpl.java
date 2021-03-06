package com.springboot.restful.survey.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.restful.survey.model.Question;
import com.springboot.restful.survey.model.Survey;
import com.springboot.restful.survey.repository.QuestionRepository;
import com.springboot.restful.survey.repository.SurveyRepository;
import com.springboot.restful.survey.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Survey getSurveyById(Long surveyId) {
		Optional<Survey> surveyOp = surveyRepository.findById(surveyId);
		
		if (surveyOp.isPresent()) {
			return surveyOp.get();
		}else {
			throw new ResourceNotFoundException("getSurveyById survey Id:" + surveyId);
		}		
	}

	@Override
	public List<Survey> getSurveyList() {
		return surveyRepository.findAll();		
	}

	@Override
	public Survey saveSurvey(Survey survey) {
		Survey savedSurvey = surveyRepository.save(survey);
		List<Question> savedQuestionList = new ArrayList<>();
		
		List<Question> questionList = survey.getQuestionList();
		for (Question question: questionList) {
			question.setSurvey(savedSurvey);
			savedQuestionList.add( questionRepository.save(question));
		}
		savedSurvey.setQuestionList(savedQuestionList);
		return savedSurvey;
	}

	@Override
	public Survey updateSurvey(Long id, Survey survey) {
		
		Survey currentsurvey = surveyRepository.findById(id).orElseThrow( 
				()-> new ResourceNotFoundException("SurveyServiceImpl/updateSurvey : Survey Id not found. -> " + survey.getId()));
			
		currentsurvey.setName(survey.getName());
		currentsurvey.setDescription(survey.getDescription());
	
		List<Question> questionList = survey.getQuestionList();
		
		Survey savedSurvey=surveyRepository.save(currentsurvey);
		
		
		List<Question> savedQuestions = new ArrayList<>();
		for(Question question:questionList) {
			
			Question ques= questionRepository.getById(question.getId());
					
			ques.setAnswerA(question.getAnswerA());
			ques.setAnswerB(question.getAnswerB());
			ques.setAnswerC(question.getAnswerC());
			ques.setAnswerD(question.getAnswerD());
			ques.setAnswerE(question.getAnswerE());
			ques.setDescription(question.getDescription());
			
			Question savedQuestion = questionRepository.save(ques);
			savedQuestions.add(savedQuestion );
		}
		
		savedSurvey.setQuestionList(savedQuestions);
		
		return savedSurvey;
		
	}

	@Override
	public void deleteSurvey(Long surveyId) {
		
		List<Question> questionList = questionRepository.findBySurveyId(surveyId);
		for(Question questionToBeDeleted: questionList) {
			questionRepository.delete(questionToBeDeleted);
		}
		
		Survey currentsurvey = surveyRepository.findById(surveyId).orElseThrow( 
				()-> new ResourceNotFoundException("SurveyServiceImpl/deleteSurvey : Survey Id not found. -> " + surveyId));
	
		surveyRepository.delete(currentsurvey);
	}

}
