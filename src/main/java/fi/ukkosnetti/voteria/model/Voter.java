package fi.ukkosnetti.voteria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"ip", "ballot"})})
public class Voter {
	
	@Id
	private Long id;
	
	@Column(updatable=false, nullable=false)
	private String ip;
	
	@ManyToOne
	@Column(updatable=false, nullable=false)
	private Ballot ballot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Ballot getBallot() {
		return ballot;
	}

	public void setBallot(Ballot ballot) {
		this.ballot = ballot;
	}

	@Override
	public String toString() {
		return "Voter [id=" + id + ", ip=" + ip + ", ballot=" + ballot + "]";
	}

}
