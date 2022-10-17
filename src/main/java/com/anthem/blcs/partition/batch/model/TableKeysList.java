package com.anthem.blcs.partition.batch.model;

import java.util.List;

import lombok.Data;

@Data
public class TableKeysList 
{
	private List <TableKeys> landingZoneList;
	private String tableName;
	//private String eventId;

}