package fi.ukkosnetti.voteria.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.voteria.model.BallotOption;

public interface BallotOptionRepository extends CrudRepository<BallotOption, Long> {

	
}
