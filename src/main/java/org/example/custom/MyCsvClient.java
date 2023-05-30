package org.example.custom;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class MyCsvClient {

	public void writeLines( Path path, List< String[] > lines ) throws IOException {
		this.writeLines( path.toString(), lines );
	}

	public void writeLines( String path, List< String[] > lines ) throws IOException {
		try ( CSVWriter writer = this.createWriter( path ) ) {
			writer.writeAll( lines );
		}
	}

	private CSVWriter createWriter( Path path ) throws IOException {
		return this.createWriter( path.toString() );
	}

	private CSVWriter createWriter( String path ) throws IOException {
		return new CSVWriter( new FileWriter( path ) );
	}

	private CSVReader createReader( Path path ) throws FileNotFoundException {
		return this.createReader( path.toString() );
	}

	private CSVReader createReader( String path ) throws FileNotFoundException {
		return new CSVReader( new FileReader( path ) );
	}

	public List< String[] > readAll( Path path ) throws IOException, CsvException {
		return this.readAll( path.toString() );
	}

	public List< String[] > readAll( String path ) throws IOException, CsvException {
		try ( CSVReader reader = this.createReader( path ) ) {
			return reader.readAll();
		}
	}
}
