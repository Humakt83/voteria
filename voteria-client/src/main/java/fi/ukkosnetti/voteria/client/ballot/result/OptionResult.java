package fi.ukkosnetti.voteria.client.ballot.result;

public class OptionResult {
	
	public final String optionName;	
	public final Double percentageOfVotes;
	public final Long totalVotes;
	
	public OptionResult(String optionName, Double percentageOfVotes, Long totalVotes) {
		this.optionName = optionName;
		this.percentageOfVotes = percentageOfVotes;
		this.totalVotes = totalVotes;
	}
	
}
