package com.anthem.blcs.partition.batch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.anthem.blcs.partition.batch.config.CdcConnectDatasource;



@SpringBootApplication
@EnableBatchProcessing
@Import({ CdcConnectDatasource.class })
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PartitioningApplication implements CommandLineRunner
{
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
		 private static Logger logger = LoggerFactory.getLogger(PartitioningApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PartitioningApplication.class, args);
	}

		@Override
	public void run(String... args) throws Exception 
	{
		logger.info(" Batch - STARTED");
		JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
				.addDate("date", new Date())
                .addLong("time",System.currentTimeMillis()).toJobParameters();
	
		
		JobExecution execution = jobLauncher.run(job, jobParameters);

		
		logger.info("Landing Zone Batch - ENDED");
		logger.info("STATUS :: "+execution.getStatus());
		
	}
}
