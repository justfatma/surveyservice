package com.springboot.restful.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restful.survey.model.Participant;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>{

}
