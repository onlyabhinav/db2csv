package com.onlyabhinav.dbtextract.export;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlyabhinav.dbtextract.config.AppConfig;
import com.onlyabhinav.dbtextract.config.TableConfig;
import com.onlyabhinav.dbtextract.domain.TableInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CSVExportUtil {

	String SAMPLE_CSV_FILE = "test.csv";
	
	@Autowired
	AppConfig config;

	public static void main(String a[]) {
		// TODO Auto-generated method stub

		new CSVExportUtil().exportToCSV_old();

	}
	
	public void exportToCSV(TableInfo table, List<Map<String, Object>> result ) {
		
		CSVFormat format = CSVFormat.DEFAULT.
				builder()
				.setDelimiter(config.getDelimiter())
				.setHeader(table.getColumns().split(","))
				.setQuoteMode(QuoteMode.ALL_NON_NULL)
				.build();
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
				CSVPrinter csvPrinter = new CSVPrinter(writer,format);
				
				) {
			
			for (Map<String, Object> item : result) {
				csvPrinter.printRecord(item.values().toArray());
			}
			
//			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
//			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

			csvPrinter.flush();
		}

		catch (Exception e) {

			log.error("Error", e);

		}
	}

	private void exportToCSV_old() {
		
		CSVFormat format = CSVFormat.DEFAULT.
				builder()
				.setDelimiter(config.getDelimiter())
				.setHeader("ID", "Name", "Designation", "Company")
				.setQuoteMode(QuoteMode.ALL_NON_NULL)
				.build();
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
				CSVPrinter csvPrinter = new CSVPrinter(writer,format);
				
				) {
			
			
			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

			csvPrinter.flush();
		}

		catch (Exception e) {

			log.error("Error", e);

		}
	}

}
