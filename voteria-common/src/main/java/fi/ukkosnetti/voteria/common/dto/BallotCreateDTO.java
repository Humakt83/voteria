package fi.ukkosnetti.voteria.common.dto;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class BallotCreateDTO implements BallotCreate {

	private String title;
	
	private Date ends;
	
	private List<String> options;
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public Date getEnds() {
		return ends;
	}
	
	@Override
	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
	@Override
	public List<String> getOptions() {
		return options;
	}
	
	@Override
	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "BallotCreateDTO [title=" + title + ", ends=" + ends + ", options=" + options + "]";
	}
	
}
