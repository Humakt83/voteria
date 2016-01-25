package fi.ukkosnetti.voteria.common.dto;

import java.util.Date;
import java.util.List;

public class BallotCreateDTO {

	private String title;
	
	private Date ends;
	
	private List<BallotOptionCreateDTO> options;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getEnds() {
		return ends;
	}
	
	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
	public List<BallotOptionCreateDTO> getOptions() {
		return options;
	}
	
	public void setOptions(List<BallotOptionCreateDTO> options) {
		this.options = options;
	}
	
	public String toString() {
		return "BallotCreateDTO [title=" + title + ", ends=" + ends + ", options=" + options + "]";
	}
	
}
