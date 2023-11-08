package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Flow的作用就是可以将多个步骤Step组合在一起然后再组装到任务Job中。
 *
 * @author DX
 */
@Component
public class FlowJobDemo {

    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob" + System.currentTimeMillis())
                .start(flow1())
                .next(step3())
                .end()
                .build();

    }

    private Step step3() {
        return stepBuilderFactory.get("flowJob:step3" + System.currentTimeMillis()).tasklet((s, c) -> {
            System.out.println("flowJob:step3开始了-----------------");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Step step2() {
        return stepBuilderFactory.get("flowJob:step2" + System.currentTimeMillis()).tasklet((s, c) -> {
            System.out.println("flowJob:step2开始了-----------------");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Step step1() {
        return stepBuilderFactory.get("flowJob:step1" + System.currentTimeMillis()).tasklet((s, c) -> {
            System.out.println("flowJob:step1开始了-----------------");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Flow flow1() {
        return new FlowBuilder<Flow>("flow1")
                .start(step1())
                .next(step2())
                .build();
    }
}
