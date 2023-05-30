package org.example.custom;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.apache.commons.lang3.ObjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class PojoCsvClient< T > {

	public void writeObjects( String path, List< T > objects ) throws IOException {

		if ( ObjectUtils.isEmpty( objects ) ) {
			throw new RuntimeException( "objects is empty" );
		}

		ColumnPositionMappingStrategy< T > mappingStrategy = new ColumnPositionMappingStrategy<>();
		mappingStrategy.setType( ( Class< ? extends T > ) objects.get( 0 ).getClass() );

		String[] fieldNames = this.getFieldNames( objects.get( 0 ).getClass() );
		mappingStrategy.setColumnMapping( fieldNames );

		try ( CSVWriter writer = this.createWriter( path ) ) {
			writer.writeNext( fieldNames );
			objects.forEach( e -> writer.writeNext( this.getFieldValues( e ) ) );
		}
	}

	private CSVWriter createWriter( Path path ) throws IOException {
		return createWriter( path.toString() );
	}

	private CSVWriter createWriter( String path ) throws IOException {
		return new CSVWriter( new FileWriter( path ) );
	}

	private String[] getFieldNames( Class< ? > clazz ) {
		return Arrays.stream( clazz.getDeclaredFields() )
				.map( Field::getName )
				.toArray( String[]::new );
	}

	private String[] getFieldValues( Object object ) {
		return Arrays.stream( object.getClass().getDeclaredFields() )
				.map( field -> {
					try {
						field.setAccessible( true );
						return field.get( object ).toString();
					} catch ( IllegalAccessException e ) {
						e.printStackTrace(); // 커스텀 예외처리
						return "";
					}
				} )
				.toArray( String[]::new );
	}
}