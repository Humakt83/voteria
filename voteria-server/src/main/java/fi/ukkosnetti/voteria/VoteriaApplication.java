package fi.ukkosnetti.voteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan
public class VoteriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteriaApplication.class, args);
	}
}
