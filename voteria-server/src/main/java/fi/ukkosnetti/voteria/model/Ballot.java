package fi.ukkosnetti.voteria.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ballot {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, updatable = true, nullable = false)
	private String title;
	
	private String creatorIp;

	@OneToMany(mappedBy="ballot", fetch = FetchType.LAZY)
	private Set<BallotOption> options = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="ballot", fetch = FetchType.LAZY)
	private Set<Voter> voters = new HashSet<>();
	
	private Date created;
	
	private Date ends;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatorIp() {
		return creatorIp;
	}

	public void setCreatorIp(String creatorIp) {
		this.creatorIp = creatorIp;
	}

	public Set<BallotOption> getOptions() {
		return options;
	}

	public void setOptions(Set<BallotOption> options) {
		this.options = options;
	}

	public Set<Voter> getVoters() {
		return voters;
	}

	public void setVoters(Set<Voter> voters) {
		this.voters = voters;
	}
	
	public Date getCreated() {
		return created;
	}
	
	@PrePersist
	private void setCreated() {
		created = new Date();
	}
	
	public Date getEnds() {
		return ends;
	}
	
	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
}
