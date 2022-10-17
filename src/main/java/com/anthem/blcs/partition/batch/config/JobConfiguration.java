package com.anthem.blcs.partition.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.anthem.blcs.partition.batch.listener.InterceptingJobExecution;
import com.anthem.blcs.partition.batch.model.TableKeys;
import com.anthem.blcs.partition.batch.model.TableKeysList;
import com.anthem.blcs.partition.batch.partitioner.TablePartitioner;
import com.google.gson.Gson;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	


	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	/*
	 * @Value("${blcs_producer_landing_zone_service_url}") private String
	 * callLandingZoneService;
	 */

	@Autowired
	@Qualifier(value = "cdcDS")
	private DataSource dataSource;

	@Autowired
	private InterceptingJobExecution interceptingJob;
	
	private TableKeysList values=null;
	
	 private static Logger logger = LoggerFactory.getLogger(JobConfiguration.class);
	@Bean
	@Autowired
	public TablePartitioner partitioner() {
		return new TablePartitioner();
		
	}

	@StepScope
	@Bean(destroyMethod = "")
	public JdbcCursorItemReader<TableKeysList> queryReader (
			@Value("#{stepExecutionContext['query']}") String query,
			@Value("#{stepExecutionContext['tableName']}") String tableName) {
		
		 
		
		logger.info("Landing Zone Reader - STARTED");
		
		JdbcCursorItemReader<TableKeysList> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql(query);
		reader.setRowMapper(new RowMapper<TableKeysList>() {

			@Override
			public TableKeysList mapRow(ResultSet rs, int rowNum) throws SQLException {

				 

				List<TableKeys> myValues = new ArrayList<>();
				TableKeys val = new TableKeys();

				int colCount = rs.getMetaData().getColumnCount();
				if(!(colCount==1 && StringUtils.isEmpty(rs.getMetaData().getColumnName(1)))) {
				Map<String, String> myMap = new HashMap<>();
				for(int i=1;i<=colCount;i++) {
					String colName=rs.getMetaData().getColumnName(i);
					String colData=rs.getString(i);
				myMap.put( colName, colData);
				}
				
				val.setDeltaRecords(myMap);
				
				myValues.add(val);
				values = new TableKeysList();
				values.setLandingZoneList(myValues);
				values.setTableName(tableName);
				}
				return values;

			}

		});

		return reader;
	}

	// Master
	@Bean
	public Step masterStep() {
		logger.info("Landing Zone Master Step - STARTED");
		return stepBuilderFactory.get("MasterStep").partitioner(slaveStep().getName(), partitioner())
				.step(slaveStep()).gridSize(100).build();
	}


	// slave step
	@Bean
	public Step slaveStep()  {
	logger.info("Landing Zone Slave Step - STARTED");
	return stepBuilderFactory.get("slaveStep").<TableKeysList, TableKeysList>chunk(1000)
	.reader(queryReader(null, null)).writer(new ItemWriter<TableKeysList>() {
		
	 @Override
	public void write(List<? extends TableKeysList> items)  {
		 
		 
			logger.info("Calling of Landing Zone - Writer - STARTED");
				
				TableKeysList request= new TableKeysList();
				List<TableKeys> records= new ArrayList<>();
				
				
			for(TableKeysList entry: items) {
				
				records.addAll(entry.getLandingZoneList());
				request.setTableName(entry.getTableName());
				
			}
				
				
			if(!CollectionUtils.isEmpty(records)) {
				request.setLandingZoneList(records);
			
			Gson gson = new Gson();
			String json = gson.toJson(request);
			logger.info("Json Value: {}", json);
			
			
			}

	}

	}).build();
	}
	@Bean
	public Job landingZoneJob() {
		logger.info("Landing Zone Job - STARTED");
		
		return  jobBuilderFactory.get("landingZoneJob").start(masterStep()).listener(interceptingJob).build();
	}
}