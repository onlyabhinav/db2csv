package com.onlyabhinav.dbtextract.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.onlyabhinav.dbtextract.domain.TableInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class GenericDataGetterDao {
	
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 
	 public List<Map<String, Object>> getDataFromTable(TableInfo table) {
		 
		// jdbcTemplate.setDefaultDateFormat("yyyy-MM-dd");

		 
		List<Map<String, Object>> result = jdbcTemplate.queryForList(queryMaker(table));
		
		log.info("Resule Size :: {}",result.size());
		 
		return result;
		 
	 }
	 
	 
	 private String queryMaker(TableInfo table) {
		 
		 StringBuilder sb = new StringBuilder("");
		 
		sb.append("SELECT ");
		sb.append(table.getColumns());
		sb.append(" FROM ");
		sb.append(table.getName());
		
		if(StringUtils.hasLength(table.getCondition().trim())) {
			sb.append(" WHERE ");
			sb.append(table.getCondition());
		}
		
		log.info("QUERY ==> {}", sb.toString());
		
		return sb.toString();
	 }

}
