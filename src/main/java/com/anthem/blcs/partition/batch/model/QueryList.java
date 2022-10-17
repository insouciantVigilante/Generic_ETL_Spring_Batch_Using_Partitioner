package com.anthem.blcs.partition.batch.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "tableName", "query", "queryModel" })
@XmlRootElement
public class QueryList {

	private List<QueryModel> queryModel;

	/**
	 * @return the queryModel
	 */
	public List<QueryModel> getLandingZoneQuery() {
		return queryModel;
	}

	/**
	 * @param queryModel the queryModel to set
	 */
	@XmlElement(name = "queryModel")
	public void setLandingZoneQuery(List<QueryModel> queryModel) {
		this.queryModel = queryModel;
	}

}