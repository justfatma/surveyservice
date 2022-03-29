package com.springboot.restful.survey.service;

import java.util.List;

import com.springboot.restful.survey.model.Participant;


public interface ParticipantService {
	
	public List<Participant> getParticipantList();
	public Participant getParticipantById(Long participantId);
	public Participant saveParticipantWithAnswers(Participant participant);
	public Participant updateParticipantWithAnswers(Participant participant);	
	public void deleteParticipantWithAnswers(Long participantId);
	
	public String getRelativeDistribution(Long questionId);
}
