package com.onlyabhinav.dbtextract.start;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.onlyabhinav.dbtextract.config.TableConfig;
import com.onlyabhinav.dbtextract.dao.GenericDataGetterDao;
import com.onlyabhinav.dbtextract.domain.TableInfo;
import com.onlyabhinav.dbtextract.export.CSVExportUtil;
import com.onlyabhinav.dbtextract.export.SQLSmartUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LaunchPad {

	@Autowired
	TableConfig config;
	
	
	@Autowired
	GenericDataGetterDao dataGetter;
	
	@Autowired
	CSVExportUtil csvUtil;
	
	 @Autowired
	 SQLSmartUtil sqlSmartUtil;
	
	public void start() {
		log.info("Starting application...");
		
		
		log.info("Tables --> {}", config.getTableList());
		
		for (TableInfo currentTable : config.getTableList())
		{
			log.info("START     for :{}",currentTable.getName());
			processTable2(currentTable);
			log.info("COMPLETED for:{}", currentTable.getName());
		}
		
	}

	
	
	private void processTable1(TableInfo currentTable) {
		List<Map<String, Object>> result = dataGetter.getDataFromTable(currentTable);
		processDBResult(currentTable, result);
	}
	
	
	private void processTable2(TableInfo currentTable) {
		SqlRowSet sqlRowset = dataGetter.getDataFromTable2(currentTable);
		sqlSmartUtil.processSQLRowSet(sqlRowset);
	}
	
	private void processDBResult(TableInfo table, List<Map<String, Object>> result ) {
		log.info("processDBResult - START for Table: {}, Result Size:{}",table.getName(),result.size());
		
		log.info("---->      keySet ==> {}",result.get(0).keySet());
		log.info("----> SINGLE ITEM ==> {}",result.get(0));
		log.info("---->     table.c ==> {}",table.getColumns());
		
		csvUtil.exportToCSV(table, result);
		
		for (Map<String, Object> item : result) {
			//log.info("ITEM = {}",item);
			
		}
	}
	
	
}
