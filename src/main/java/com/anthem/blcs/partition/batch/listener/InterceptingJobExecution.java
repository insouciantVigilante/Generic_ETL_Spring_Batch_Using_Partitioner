package com.anthem.blcs.partition.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class InterceptingJobExecution implements JobExecutionListener {

	
	 private static Logger logger = LoggerFactory.getLogger(InterceptingJobExecution.class);

	
	@Override
	public void beforeJob(JobExecution jobExecution) {

		logger.info("Intercepting Job Excution - Before Job!");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("Intercepting Job Excution - After Job!");
		

	}


	


}