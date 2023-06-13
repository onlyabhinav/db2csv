package com.onlyabhinav.dbtextract.export;

import static java.sql.Types.DATE;
import static java.sql.Types.TIMESTAMP;
import static java.sql.Types.TIMESTAMP_WITH_TIMEZONE;

import java.util.Arrays;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SQLSmartUtil {

	Integer[] DATE_TYPES = { DATE, TIMESTAMP, TIMESTAMP_WITH_TIMEZONE };

	// SqlRowSet
	public void processSQLRowSet(final SqlRowSet rowset) {

		SqlRowSetMetaData rowMetadata = rowset.getMetaData();

		int colCount = rowMetadata.getColumnCount();
		String[] columns = rowMetadata.getColumnNames();
		rowMetadata.getColumnTypeName(colCount);
		// log.info("DATE_TYPES = {}", DATE_TYPES);

		for (String col : columns) {
			int colIndex = Arrays.asList(columns).indexOf(col);

			Integer colType = rowMetadata.getColumnType(colIndex + 1);

			// log.info("COLUMN INFO ==> Column={}, TypeIdx={}, TypeName={} ",
			// columns[colIndex], colType,
			// rowMetadata.getColumnTypeName(colIndex + 1));

			if (Arrays.asList(DATE_TYPES).contains(colType)) {

				log.info("DATE TYPE FOUND ==> Column={}, Type={} ", columns[colIndex],
						rowMetadata.getColumnTypeName(colIndex + 1));
			}
		}

		while (rowset.next()) {
			// log.info("[SqlRowSet] ==> {}", rowset.getRow());

		}

	}

}
