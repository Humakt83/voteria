package fi.ukkosnetti.voteria.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface Ballot extends Serializable {

	String getTitle();
	
	void setTitle(String title);
	
	Date getCreated();
	
	void setCreated(Date created);
	
	Date getEnds();
	
	void setEnds(Date ends);
	
	List<BallotOptionDTO> getOptions();
	
	void setOptions(List<BallotOptionDTO> options);
	
}
