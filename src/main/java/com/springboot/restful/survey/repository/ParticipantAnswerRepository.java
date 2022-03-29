package com.springboot.restful.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restful.survey.model.ParticipantAnswer;


@Repository
public interface ParticipantAnswerRepository extends JpaRepository<ParticipantAnswer, Long>{

	List<ParticipantAnswer> findByParticipantId(Long participantId);

}
