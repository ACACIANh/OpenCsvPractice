package org.example.custom;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class PojoCsvClientTest {
	String testPath = "./test.csv";
	private static PojoCsvClient< Player > playerPojoCsvClient;

	@BeforeAll
	static void init() {
		playerPojoCsvClient = new PojoCsvClient<>();
	}

	@Test
	void writePojo() throws IOException {

		List< Player > players = Arrays.asList(
				new Player( 0, "acacian", "ha" ),
				new Player( 1, "s4ng", "lee" )
		);

		playerPojoCsvClient.writeObjects( testPath, players );
	}
}