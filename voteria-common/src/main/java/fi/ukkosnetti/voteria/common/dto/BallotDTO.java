package fi.ukkosnetti.voteria.common.dto;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class BallotDTO implements Ballot {
	
	private String title;
	
	private Date created;
	
	private Date ends;
	
	private List<BallotOptionDTO> options;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getEnds() {
		return ends;
	}
	
	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
	public List<BallotOptionDTO> getOptions() {
		return options;
	}
	
	public void setOptions(List<BallotOptionDTO> options) {
		this.options = options;
	}

}
