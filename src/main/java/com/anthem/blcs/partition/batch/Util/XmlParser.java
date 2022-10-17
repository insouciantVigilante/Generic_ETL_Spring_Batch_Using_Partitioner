package com.anthem.blcs.partition.batch.Util;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anthem.blcs.partition.batch.model.QueryList;
import com.anthem.blcs.partition.batch.partitioner.TablePartitioner;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlParser {

	private static Logger logger = LoggerFactory.getLogger(TablePartitioner.class);
	
	
	public  QueryList deserializeFromXML() {
	
		logger.info("Xml parsing to fetch the Landing Zone Tables - STARTED");
		QueryList deserializedData=null;
		    try {
		        XmlMapper xmlMapper = new XmlMapper();
		        // read file and put contents into the string
		        String readContent = new String(Files.readAllBytes(Paths.get("C:/Code/OracleQueries.xml")));


		        // deserialize from the XML into a Phone object
		       deserializedData = xmlMapper.readValue(readContent, QueryList.class);

		      
		    } catch (Exception e) {
		    	logger.info("Exception occured at - Landing Zone Xml Parser", e.getMessage());
				
		    }
		   return deserializedData;
		}
	 	
	
}
