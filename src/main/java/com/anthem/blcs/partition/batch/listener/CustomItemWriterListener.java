package com.anthem.blcs.partition.batch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.anthem.blcs.partition.batch.model.TableKeysList;

public class CustomItemWriterListener implements ItemWriteListener<TableKeysList> {

    @Override
    public void beforeWrite(List<? extends TableKeysList> items) {
    
    }

    @Override
    public void afterWrite(List<? extends TableKeysList> items) {
        
    }

    @Override
    public void onWriteError(Exception exception, List<? extends TableKeysList> items) {
       
    	try {
    	       throw new Exception("");
    	    	}	
    	    	catch (Exception e) {
    	    		
    	    	}
    }

}