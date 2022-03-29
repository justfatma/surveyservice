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
import com.springboot.restful.survey.model.Participant;
import com.springboot.restful.survey.serviceImpl.ParticipantServiceImpl;

@RestController
public class ParticipantController {

	
	@Autowired
	private ParticipantServiceImpl participantServiceImpl;
	
	
	@GetMapping("participants")
	public List<Participant> getParticipantList() {
				
		List<Participant> list= participantServiceImpl.getParticipantList();
    	if (list!= null && list.size()>0) {
    		return list;
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
    	}
	}
	
	@PostMapping("participants")
	public ResponseEntity<Participant> saveParticipantWithAnswers(@RequestBody Participant participant) {
		
		return new ResponseEntity<Participant> (participantServiceImpl.saveParticipantWithAnswers(participant), HttpStatus.CREATED);
	} 
	
	@PutMapping("participants")
	public ResponseEntity<Participant> updateParticipantWithAnswers(@RequestBody Participant participant) {
		
		return new ResponseEntity<Participant> (participantServiceImpl.updateParticipantWithAnswers(participant), HttpStatus.OK);
	}
	
	
	@DeleteMapping("participants/{participantId}")
	public ResponseEntity<String> deleteParticipantWithAnswers(@PathVariable Long participantId) {
		participantServiceImpl.deleteParticipantWithAnswers(participantId);
		return new ResponseEntity<String> ("Participant is deleted successfully!", HttpStatus.OK);
	}
	
	@GetMapping("relative/{questionId}")
	public ResponseEntity<String> getRelativeDistributionByQuestionId(@PathVariable Long questionId) {
				
		String str= participantServiceImpl.getRelativeDistribution(questionId);
    	return new ResponseEntity<String> (str, HttpStatus.OK);
	}
}
