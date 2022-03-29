package com.springboot.restful.survey;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.springboot.restful.survey.model.Answer;
import com.springboot.restful.survey.model.Participant;
import com.springboot.restful.survey.model.ParticipantAnswer;
import com.springboot.restful.survey.model.Question;
import com.springboot.restful.survey.model.Survey;
import com.springboot.restful.survey.serviceImpl.ParticipantServiceImpl;
import com.springboot.restful.survey.serviceImpl.SurveyServiceImpl;

@SpringBootTest
class SurveyserviceApplicationTests {
	
	@Autowired
	private SurveyServiceImpl surveyServiceImpl;	
	
	@Autowired
	private ParticipantServiceImpl participantServiceImpl;	
	
	public Survey setUpSurvey() {
		Question q1= new Question("Who does the grocery shopping at home?", "Mum", "Dad", "Wife", "Husband", "Myself");
		Question q2= new Question("What are the products you consume most at home weekly?", "Fruit", "Vegetable", "Meat", "Dessert", "Pastry");
		Question q3= new Question("Do you cook at home?", "Seldom", "A few times in a month", "A few times in a week", "Usually", "Every day");
				
		List<Question> questionList= new ArrayList<>();
		questionList.add(q1);
		questionList.add(q2);
		questionList.add(q3);
		
 		Survey survey = new Survey("Grocery shopping", "Weekly consumption and eating habits", questionList);
 			
 		Survey savedSurvey = surveyServiceImpl.saveSurvey(survey);
 		
 		return savedSurvey;
	}
	
	public Participant setUpParticipant() {
		
		Survey savedSurvey = setUpSurvey();
		
		ParticipantAnswer a1= new ParticipantAnswer(savedSurvey.getQuestionList().get(0), Answer.B);
		ParticipantAnswer a2= new ParticipantAnswer(savedSurvey.getQuestionList().get(1), Answer.C);
		ParticipantAnswer a3= new ParticipantAnswer(savedSurvey.getQuestionList().get(2), Answer.A);
				
		List<ParticipantAnswer> answerList= new ArrayList<>();
		answerList.add(a1);
		answerList.add(a2);
		answerList.add(a3);
		
 		Participant participant = new Participant("Alice","Wonder", "23", answerList);
 			
 		Participant savedPar = participantServiceImpl.saveParticipantWithAnswers(participant);
 		
 		return savedPar;
		
	}

	@Test
	public void shouldInsertSurveyWithQuestions() {
 		 		
		Survey savedSurvey = setUpSurvey();
 		List<Question> qlist = savedSurvey.getQuestionList();
 		
 		assertAll(
				 () -> assertEquals("Weekly consumption and eating habits", savedSurvey.getDescription()),
				 () -> assertEquals("Grocery shopping", savedSurvey.getName()),
				 () -> assertEquals(3, qlist.size())
				);
	}
		
	
	@Test
	public void shouldGetSurveyWithQuestions() {
	
	      Survey survey = surveyServiceImpl.getSurveyById(setUpSurvey().getId());
	      assertEquals("Grocery shopping", survey.getName());
	}
	
	@Test
	public void shouldInsertParticipantWithAnswers() {
	     
		Participant participant= setUpParticipant();
		
		assertAll(
				 () -> assertEquals(participant.getName(), "Alice"),
				 () -> assertEquals(participant.getSurname(), "Wonder"),
				 () -> assertEquals(participant.getAge(), "23")
				);	
	}
	
	
	@Test
	public void shouldDeleteParticipantWithAnswers() {
	      
		Participant participant= setUpParticipant();
		participantServiceImpl.deleteParticipantWithAnswers(participant.getId());
		
		try {
			participantServiceImpl.getParticipantById(participant.getId());
			
		} catch (ResourceNotFoundException e) {
			assertEquals("getParticipantById participant Id:" + participant.getId(), e.getMessage());
		}
	}
	
	@Test
	public void shouldDeleteSurveyWithAnswers() {
	     
		Survey survey = setUpSurvey();
		surveyServiceImpl.deleteSurvey(survey.getId());
		
		try {
			surveyServiceImpl.getSurveyById(survey.getId());
			
		} catch (ResourceNotFoundException e) {
			assertEquals("getSurveyById survey Id:" + survey.getId(), e.getMessage());
		}
	}
}
