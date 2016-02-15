package fi.ukkosnetti.voteria;

import java.io.IOException;

import org.junit.Test;

import fi.ukkosnetti.coverage.Coverager;



public class VoteriaApplicationTest {

	@Test
	public void testAll() throws IOException {
		new Coverager().coverage();
	}
}
