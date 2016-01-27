package fi.ukkosnetti.voteria.client.ballot.result;

import java.util.List;

public class Result {

	public final Long totalVotes;
	public final List<OptionResult> optionResults;
	
	public Result(Long totalVotes, List<OptionResult> optionResults) {
		this.totalVotes = totalVotes;
		this.optionResults = optionResults;
	}
	
}
