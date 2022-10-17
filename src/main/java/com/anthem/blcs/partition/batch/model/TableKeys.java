package com.anthem.blcs.partition.batch.model;

import java.util.Map;

import lombok.Data;

@Data
public class TableKeys 
{
	private Map<String, String> deltaRecords;

}