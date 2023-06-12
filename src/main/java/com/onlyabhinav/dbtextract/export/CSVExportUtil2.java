package com.onlyabhinav.dbtextract.export;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CSVExportUtil2 {

	String SAMPLE_CSV_FILE = "test.csv";
	
	

	public static void main(String a[]) {
		// TODO Auto-generated method stub

		new CSVExportUtil2().exportToCSV();

	}

	public void exportToCSV() {
		char delim = '|'; 
		
		CSVFormat format = CSVFormat.DEFAULT.
				builder()
				.setDelimiter(delim)
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
