package com.anthem.blcs.partition.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import com.anthem.blcs.partition.batch.model.TableKeysList;

public class CustomItemReaderListener implements ItemReadListener<TableKeysList> {

    @Override
    public void beforeRead() {
       
    }

    @Override
    public void afterRead(TableKeysList item) {
       
    }

    @Override
    public void onReadError(Exception ex) {
    	try {
       throw new Exception("");
    	}	
    	catch (Exception e) {
    		
    	}
    }

}