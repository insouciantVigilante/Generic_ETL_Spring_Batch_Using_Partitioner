package com.anthem.blcs.partition.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepListener;
import org.springframework.lang.Nullable;

public interface StepExecutionListener extends StepListener {

    void beforeStep(StepExecution var1);

    @Nullable
    ExitStatus afterStep(StepExecution var1);
}