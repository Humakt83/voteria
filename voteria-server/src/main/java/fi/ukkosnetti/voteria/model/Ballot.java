package fi.ukkosnetti.voteria.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ballot {

	@Id
	private Long id;
	
	@Column(unique = true, updatable = true, nullable = false)
	private String title;
	
	private String creatorIp;

	@OneToMany(mappedBy="ballot")
	private Set<BallotOption> options;
	
	@OneToMany(mappedBy="ballot")
	private Set<Voter> voters = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
