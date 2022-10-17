package com.anthem.blcs.partition.batch.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import com.anthem.blcs.partition.batch.Util.XmlParser;
import com.anthem.blcs.partition.batch.model.QueryList;
import com.anthem.blcs.partition.batch.model.QueryModel;

public class TablePartitioner implements Partitioner {

	private static Logger logger = LoggerFactory.getLogger(TablePartitioner.class);

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		logger.info("Landing Zone Table Partitioner - STARTED");
		XmlParser parse = new XmlParser();
		QueryList queries = parse.deserializeFromXML();

		Map<String, ExecutionContext> landingZoneTables = new HashMap<>();
		int num = 0;
		for (QueryModel entry : queries.getLandingZoneQuery()) {
			ExecutionContext value = new ExecutionContext();
			value.put("query", entry.getQuery());
			value.put("tableName", entry.getTableName());

			landingZoneTables.put("partition" + num, value);
			num++;
		}

		return landingZoneTables;
	}

}