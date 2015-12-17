package fi.ukkosnetti.voteria.common.dto;

public class BallotOptionDTO {

	private String optionName;
	
	private Long votes;
	
	public String getOptionName() {
		return optionName;
	}
	
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	public Long getVotes() {
		return votes;
	}
	
	public void setVotes(Long votes) {
		this.votes = votes;
	}
}
