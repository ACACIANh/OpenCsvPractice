package org.example.custom;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class MyCsvClientTest {

	String testPath = "./test.csv";
	private static MyCsvClient csvClient;

	@BeforeAll
	static void init() {
		csvClient = new MyCsvClient();
	}

	@Test
	void writerTest() throws IOException {

		List< String[] > dataLines = List.of(
				new String[]{ "a0", "", "a3" },
				new String[]{ "      ", null, "f6" },
				new String[]{ "d4", "d5", "\n" }
		);

		csvClient.writeLines( testPath, dataLines );
	}

	@Test
	void readerTest() throws IOException, CsvException {

		List< String[] > strings = csvClient.readAll( testPath );

		strings.forEach( e -> System.out.println( Arrays.toString( e ) ) );
	}

}