package com.example.demo.component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author dongxu
 * @create 2023-04-15 下午1:30
 */
@Data
@Slf4j
@Component
public class ScheduleTask implements SchedulingConfigurer {

    @Value("${print-time.cron}")
    private String cron;

    private Long timer = 10 * 1000L;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 动态使用cron表达式设置循环间隔
        taskRegistrar.addTriggerTask(
                // 任务执行
                this::doLog,
                triggerContext -> {
//                    // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
//                    CronTrigger cronTrigger = new CronTrigger(cron);
//                    // 下次执行的时间
//                    return cronTrigger.nextExecutionTime(triggerContext);
                    // 另一种触发器，不需要输入cron表达式，而是输入任意毫秒。
                    PeriodicTrigger periodicTrigger = new PeriodicTrigger(timer);
                    return periodicTrigger.nextExecutionTime(triggerContext);
                });
    }

    private void doLog() {
        log.info("Current time： {}", LocalDateTime.now());
    }

}
