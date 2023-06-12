package com.onlyabhinav.dbtextract.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.onlyabhinav.dbtextract.domain.TableInfo;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("mytables")
@Component
@Getter
@Setter
public class TableConfig {

    private final List<TableInfo> tableList = new ArrayList<>();

    
}
