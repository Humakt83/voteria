package fi.ukkosnetti.voteria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.ukkosnetti.voteria.common.dto.BallotCreateDTO;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.model.Ballot;
import fi.ukkosnetti.voteria.repository.BallotOptionRepository;
import fi.ukkosnetti.voteria.repository.BallotRepository;

@Service
@Transactional
public class BallotService {

	@Autowired
	private BallotRepository repository;
	
	@Autowired
	private BallotOptionRepository optionRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public Long create(BallotCreateDTO dto, String ip) {
		Ballot ballot = mapper.convertValue(dto, Ballot.class);
		ballot.setCreatorIp(ip);
		ballot = repository.save(ballot);
		saveOptions(ballot);		
		return ballot.getId();
	}

	public BallotDTO get(Long id) {
		Ballot ballot = repository.findOne(id);
		return mapper.convertValue(ballot, BallotDTO.class);		
	}
	
	public List<BallotDTO> all() {
		List<Ballot> ballots = new ArrayList<>();
		repository.findAll().forEach(ballots::add);
		return ballots.stream()
				.map(ballot -> mapper.convertValue(ballot, BallotDTO.class))
				.collect(Collectors.toList());
	}
	
	private void saveOptions(final Ballot ballot) {
		ballot.getOptions().stream().map(option -> {
			option.setBallot(ballot);
			return option;
		}).forEach(optionRepository::save);
	}
	
}
