package com.example.demo.controller;

import com.example.demo.component.ScheduleTask;
import com.example.demo.pojo.result.ResultBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dongxu
 * @create 2023-04-15 下午1:28
 */
@Slf4j
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Resource
    private ScheduleTask scheduleTask;

    @GetMapping(value = "/updateCron")
    @ApiModelProperty(value = "修改定时任务cron表达式")
    public ResultBean updateCron(String cron) {
        log.info("new cron :{}", cron);
        scheduleTask.setCron(cron);
        return ResultBean.success();
    }

    @GetMapping(value = "/updateTimer")
    @ApiModelProperty(value = "修改定时任务间隔时间")
    public ResultBean updateCron(Long timer) {
        log.info("new timer :{}", timer);
        scheduleTask.setTimer(timer);
        return ResultBean.success();
    }
}
