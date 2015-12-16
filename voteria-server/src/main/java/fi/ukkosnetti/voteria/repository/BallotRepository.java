package fi.ukkosnetti.voteria.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.voteria.model.Ballot;

public interface BallotRepository extends CrudRepository<Ballot, Long> {

}
