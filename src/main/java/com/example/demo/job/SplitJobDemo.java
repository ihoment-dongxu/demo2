package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 并行执行
 *
 * @author DX
 */
@Component
public class SplitJobDemo {
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job splitJob() {
        System.out.println("splitJob开始");
        return jobBuilderFactory.get("splitJob" + System.currentTimeMillis())
                .start(flow1())
                // 需要指定一个线程池
                .split(new SimpleAsyncTaskExecutor()).add(flow2())
                .end()
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("splitJob:step1 执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("splitJob:step2 执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("splitJob:step3 执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Flow flow1() {
        return new FlowBuilder<Flow>("flow1" + System.currentTimeMillis())
                .start(step1())
                .next(step2())
                .build();
    }

    private Flow flow2() {
        return new FlowBuilder<Flow>("flow2" + System.currentTimeMillis())
                .start(step3())
                .build();
    }
}
