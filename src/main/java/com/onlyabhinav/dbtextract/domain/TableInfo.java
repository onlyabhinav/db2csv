package com.onlyabhinav.dbtextract.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableInfo {
	
	private String name;
	private String columns;
	private String condition;

}
