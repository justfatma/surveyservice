package com.springboot.restful.survey.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.restful.survey.model.Answer;
import com.springboot.restful.survey.model.Participant;
import com.springboot.restful.survey.model.ParticipantAnswer;
import com.springboot.restful.survey.model.Question;
import com.springboot.restful.survey.repository.ParticipantAnswerRepository;
import com.springboot.restful.survey.repository.ParticipantRepository;
import com.springboot.restful.survey.repository.QuestionRepository;
import com.springboot.restful.survey.service.ParticipantService;

@Service
public class ParticipantServiceImpl implements ParticipantService{

	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private ParticipantAnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private EntityManager entityManager;

	
	@Override
	public List<Participant> getParticipantList() {
		
		return participantRepository.findAll();
	}

	@Override
	public Participant saveParticipantWithAnswers(Participant participant) {
		
		Participant savedParticipant = participantRepository.save(participant);
		
		List<ParticipantAnswer> savedAnswerList = new ArrayList<>();
		
		List<ParticipantAnswer> answerList = participant.getAnswerList();
		
		for (ParticipantAnswer answer: answerList) {
			
			if (answer.getQuestion() == null || answer.getQuestion().getId() == null) {
				throw new ResourceNotFoundException("saveParticipantWithAnswers : Question Id can't be empty. -> ");
			}
			
			Question ques = questionRepository.findById(answer.getQuestion().getId()).orElseThrow( 
					()-> new ResourceNotFoundException("saveParticipantWithAnswers : Question Id is not found. -> " + answer.getQuestion().getId()));
			answer.setQuestion(ques);
			
			answer.setParticipant(savedParticipant);
			savedAnswerList.add( answerRepository.save(answer));
		}
		savedParticipant.setAnswerList(savedAnswerList);
		
		return savedParticipant;
	}

	@Override
	public Participant updateParticipantWithAnswers(Participant participant) {
		
		Participant currentparticipant = participantRepository.findById(participant.getId()).orElseThrow( 
				()-> new ResourceNotFoundException("ParticipantServiceImpl/updateParticipantAnswers : Participant Id not found. -> " + participant.getId()));
			
		currentparticipant.setAge(participant.getAge());
		currentparticipant.setName(participant.getName());
		currentparticipant.setSurname(participant.getSurname());
		
		List<ParticipantAnswer> answerList = participant.getAnswerList();
		
		Participant savedParticipant = participantRepository.save(currentparticipant);
		
		
		List<ParticipantAnswer> savedAnswers=new ArrayList<>();
		for(ParticipantAnswer answer:answerList) {
			
			ParticipantAnswer pAnswer = answerRepository.getById(answer.getId());
					
			pAnswer.setAnswer(answer.getAnswer());
			
			
			ParticipantAnswer savedAnswer = answerRepository.save(pAnswer);
			savedAnswers.add(savedAnswer);
		}
		
		savedParticipant.setAnswerList(savedAnswers);
		
		return savedParticipant;
	}

	@Override
	public void deleteParticipantWithAnswers(Long participantId) {
		
		List<ParticipantAnswer> answerList = answerRepository.findByParticipantId(participantId);
		for(ParticipantAnswer answerToBeDeleted: answerList) {
			answerRepository.delete(answerToBeDeleted);
		}
		
		Participant currentParticipant= participantRepository.findById(participantId).orElseThrow( 
				()-> new ResourceNotFoundException("ParticipantServiceImpl/deleteParticipantWithAnswers : Participant Id not found. -> " + participantId));
	
		participantRepository.delete(currentParticipant);
		
	}

	@Override
	public String getRelativeDistribution(Long questionId) {
			
		// WITH NATIVE QUERY			
		StringBuffer sb=new StringBuffer("SELECT ANSWER FROM PARTICIPANT_ANSWER "
										+ "WHERE QUESTION_ID = ?1  ");

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter(1, questionId);
				
		List<Object> answerList = (List<Object>)query.getResultList();	
		
		if (answerList==null || answerList.isEmpty()) {
			throw new ResourceNotFoundException("getRelativeDistribution : Answers are not found with question Id: -> " + questionId);
		}
		
		Iterator itr = answerList.iterator();
		
		System.out.println(answerList.size());
				
		int countA=0;
		int countB=0;
		int countC=0;
		int countD=0;
		int countE=0;
		int totalCount=answerList.size();
		
		while(itr.hasNext()){
			
			Object obj = (Object) itr.next();
		    Answer answer = Answer.valueOf(String.valueOf(obj));
			
			switch (answer) {
			  case A:
				countA++;
			    break;
			  case B:
				countB++;
			    break;
			  case C:
				countC++;
			    break;
			  case D:
				countD++;
			    break;
			  case E:
				countE++;
			    break;
			}
		}
				
		TreeMap<Answer, String> map = new TreeMap<>();
		map.put(Answer.A, String.format( "%.2f", (((double)countA) / totalCount) * 100 ) + "%" );
		map.put(Answer.B, String.format( "%.2f", (((double)countB) / totalCount) * 100 ) + "%" );
		map.put(Answer.C, String.format( "%.2f", (((double)countC) / totalCount) * 100 ) + "%" );
		map.put(Answer.D, String.format( "%.2f", (((double)countD) / totalCount) * 100 ) + "%" );
		map.put(Answer.E, String.format( "%.2f", (((double)countE) / totalCount) * 100 ) + "%" );
			
		System.out.println(map.toString());
		return map.toString();		
	}

	@Override
	public Participant getParticipantById(Long participantId) {
		Optional<Participant> participantOp = participantRepository.findById(participantId);
		
		if (participantOp.isPresent()) {
			return participantOp.get();
		}else {
			throw new ResourceNotFoundException("getParticipantById participant Id:" + participantId);
		}
	}

}
