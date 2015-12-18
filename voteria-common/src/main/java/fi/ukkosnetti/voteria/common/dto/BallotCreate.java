package fi.ukkosnetti.voteria.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface BallotCreate extends Serializable {

	void setOptions(List<String> options);

	List<String> getOptions();

	void setEnds(Date ends);

	Date getEnds();

	void setTitle(String title);

	String getTitle();

}
