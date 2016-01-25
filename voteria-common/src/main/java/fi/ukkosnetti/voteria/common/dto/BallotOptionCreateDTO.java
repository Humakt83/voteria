package fi.ukkosnetti.voteria.common.dto;

public class BallotOptionCreateDTO {

	private String optionName;
	
	public BallotOptionCreateDTO() {
	}
	
	public BallotOptionCreateDTO(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionName() {
		return optionName;
	}
	
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
}
