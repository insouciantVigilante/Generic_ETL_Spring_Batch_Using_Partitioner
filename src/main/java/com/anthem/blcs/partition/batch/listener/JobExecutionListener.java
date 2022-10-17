/**
 * 
 */
package com.anthem.blcs.partition.batch.listener;

import org.springframework.batch.core.JobExecution;

/**
 * @author AG91132
 *
 */
public interface JobExecutionListener {
	 
    void beforeJob(JobExecution jobExecution);
    void afterJob(JobExecution jobExecution);
 
}