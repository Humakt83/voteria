package fi.ukkosnetti.voteria.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.voteria.model.Voter;

public interface VoterRepository extends CrudRepository<Voter, Long> {

	Voter findByIp(String ip);
	
}
