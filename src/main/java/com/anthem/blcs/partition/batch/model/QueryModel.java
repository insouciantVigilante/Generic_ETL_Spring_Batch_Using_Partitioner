package com.anthem.blcs.partition.batch.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "tableName", "query", "landingZoneQuery" })
public class QueryModel {
	String tableName;
	String query;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}