package com.example.demo.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 任务决策器
 *
 * @author DX
 */
@Component
public class MyDecider implements JobExecutionDecider {
    /**
     * 自定义决策条件
     */
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.WEDNESDAY) {
            return new FlowExecutionStatus("wed");
        } else {
            return new FlowExecutionStatus("other");
        }
    }
}
