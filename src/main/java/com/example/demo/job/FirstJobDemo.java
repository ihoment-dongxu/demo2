package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 第一个任务
 *
 * @author DX
 */
@Component
public class FirstJobDemo {
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("firstJob").start(step()).build();
    }

    private Step step() {
        return stepBuilderFactory.get("firstStep").tasklet((contribution, chunkContext) -> {
            System.out.println("执行步骤....");
            // 必须返回一个明确的执行状态
            return RepeatStatus.FINISHED;
        }).build();
    }
}
