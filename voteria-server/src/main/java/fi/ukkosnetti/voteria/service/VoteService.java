package fi.ukkosnetti.voteria.service;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.voteria.model.Ballot;
import fi.ukkosnetti.voteria.model.BallotOption;
import fi.ukkosnetti.voteria.model.Voter;
import fi.ukkosnetti.voteria.repository.BallotOptionRepository;
import fi.ukkosnetti.voteria.repository.BallotRepository;
import fi.ukkosnetti.voteria.repository.VoterRepository;

@Service
@Transactional
public class VoteService {

	@Autowired
	private BallotRepository ballotRepository;
	
	@Autowired
	private BallotOptionRepository optionRepository;
	
	@Autowired
	private VoterRepository voterRepository;
	
	public void vote(String ballotTitle, String option, String ip) {
		Ballot ballot = ballotRepository.findByTitle(ballotTitle);
		assertVoteIsValid(ballot, option, ip);
		ballot.getVoters().add(addVoter(ballot, ip));
		addVote(ballot, option);
		ballotRepository.save(ballot);
	}

	private void addVote(final Ballot ballot, final String optionName) {
		BallotOption option = ballot.getOptions().stream().filter(op -> op.getOptionName().equals(optionName)).findAny().get();
		option.setVotes(option.getVotes() + 1);
		optionRepository.save(option);
	}

	private Voter addVoter(Ballot ballot, String ip) {
		Voter voter = new Voter();
		voter.setIp(ip);
		voter.setBallot(ballot);		
		return voterRepository.save(voter);
	}

	private void assertVoteIsValid(Ballot ballot, String option, final String ip) {
		if (ballot.getEnds().before(Calendar.getInstance().getTime())) {
			throw new IllegalArgumentException("Ballot has been closed");
		}
		if (ballot.getVoters().stream().anyMatch(voter -> voter.getIp().equals(ip))) {
			throw new IllegalArgumentException("Already voted!");
		}
		if (ballot.getOptions().stream().noneMatch(op -> op.getOptionName().equals(option))) {
			throw new IllegalArgumentException("No such option: " + option);
		}
	}
}
