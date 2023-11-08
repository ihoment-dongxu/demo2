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
 * 使用决策器demo
 *
 * @author DX
 */
@Component
public class DeciderJobDemo {
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Resource
    private MyDecider myDecider;

    @Bean
    public Job deciderJob() {
        System.out.println("deciderJob开始。。。。。。。。。。。。。。。");
        return jobBuilderFactory.get("deciderJob")
                .start(step1())
                .next(myDecider)
                // if
                .from(myDecider).on("wed").to(step3())
                // else if
                .from(myDecider).on("other").to(step4())
                // else
                .from(step3()).on("*").to(step2())
                .end().build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("DeciderJob:step1 执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("DeciderJob:step2 执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("DeciderJob:step3 执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step4() {
        return stepBuilderFactory.get("step4" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("DeciderJob:step4 执行步骤四操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
