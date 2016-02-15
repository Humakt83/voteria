package fi.ukkosnetti.voteria;

import java.io.IOException;

import org.junit.Test;

import fi.ukkosnetti.supercoveragetest.SuperCoverageTest;

public class VoteriaApplicationTest {

	@Test
	public void testAll() throws IOException {
		SuperCoverageTest.testAllPrintFiles();
	}
}
