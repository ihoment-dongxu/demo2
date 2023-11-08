package com.example.demo.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 多步骤任务
 * 一个复杂的任务一般包含多个步骤
 *
 * @author DX
 */
@Component
public class MultiStepJobDemo {
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Resource
    private JobBuilderFactory jobBuilderFactory;

//    @Bean
//    public Job multiStepJob() {
//        return jobBuilderFactory.get("multiStepJob" + System.currentTimeMillis())
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }


    @Bean
    public Job multiStepJob2() {
        System.out.println("multiStepJob2开始。。。。。");
        return jobBuilderFactory.get("multiStepJob2" + System.currentTimeMillis())
                .start(step1())
                // ExitStatus.COMPLETED常量表示任务顺利执行完毕
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(step2())
                .from(step2())
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(step3())
                .from(step3())
                .end()
                .build();
    }


    private Step step2() {
        return stepBuilderFactory.get("step2" + System.currentTimeMillis()).tasklet((s, c) -> {
            System.out.println("执行步骤二操作。。。");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1" + System.currentTimeMillis()).tasklet((s, c) -> {
            System.out.println("执行步骤一操作。。。");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3" + System.currentTimeMillis())
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
