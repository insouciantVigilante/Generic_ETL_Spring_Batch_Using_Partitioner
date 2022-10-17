package com.anthem.blcs.partition.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;


@Component
public class SPStepListener implements StepExecutionListener {
	

	

    @Override
    public void beforeStep(StepExecution stepExecution) {
    	System.out.println("Hi before step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
       
    	System.out.println("Hi After step");

		return ExitStatus.COMPLETED;
    }
    
    
   
}