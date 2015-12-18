package fi.ukkosnetti.voteria.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.ukkosnetti.voteria.common.dto.BallotCreate;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.model.Ballot;
import fi.ukkosnetti.voteria.repository.BallotRepository;

@Service
@Transactional
public class BallotService {

	@Autowired
	private BallotRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public Long create(BallotCreate dto, String ip) {
		Ballot ballot = mapper.convertValue(dto, Ballot.class);
		ballot.setCreatorIp(ip);
		return repository.save(ballot).getId();
	}
	
	public BallotDTO get(Long id) {
		Ballot ballot = repository.findOne(id);
		return mapper.convertValue(ballot, BallotDTO.class);		
	}
	
}