package fi.ukkosnetti.voteria.client.ballot.result;

import java.util.ArrayList;
import java.util.List;

import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.common.dto.BallotOptionDTO;

public class ResultCalculator {

	public static Result calculateResults(BallotDTO dto) {
		List<OptionResult> optionResults = new ArrayList<OptionResult>();
		long totalVotes = 0l;
		for (BallotOptionDTO option : dto.getOptions()) {
			totalVotes += option.getVotes();
		}
		totalVotes = totalVotes < 1 ? 1l : totalVotes;
		for (BallotOptionDTO option : dto.getOptions()) {
			Double percentageOfVotes = ((double)option.getVotes() / totalVotes) * 100;
			optionResults.add(new OptionResult(option.getOptionName(), percentageOfVotes, option.getVotes()));
		}
		return new Result(totalVotes, optionResults);
	}
}
