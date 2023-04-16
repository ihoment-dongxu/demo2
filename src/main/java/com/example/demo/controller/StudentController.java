package com.example.demo.controller;

import com.example.demo.pojo.result.ResultBean;
import com.example.demo.pojo.vo.school.StudentVO;
import com.example.demo.service.student.StudentService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author dongxu
 * @create 2023-04-09 下午9:02
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping
    public ResultBean<StudentVO> getStudent() {
        StudentVO studentVO = studentService.getStudent();
        return ResultBean.ok(studentVO);
    }

    @GetMapping(value = "/list")
    public ResultBean<List<StudentVO>> listStudent() {
        List<StudentVO> voList = studentService.listStudent();
        return ResultBean.ok(voList);
    }

    @SneakyThrows
    @GetMapping(value = "/async/list")
    public ResultBean<List<StudentVO>> asyncListStudent() {
        CompletableFuture<List<StudentVO>> listCompletableFuture = CompletableFuture.supplyAsync(() -> studentService.listStudent());
        List<StudentVO> studentVOS = listCompletableFuture.get();
        return ResultBean.ok(studentVOS);
    }


    /**
     * value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；
     * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，
     *          则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     * @return
     */
    @GetMapping(value = "/retry/list")
    public ResultBean<List<StudentVO>> retryListStudent() {
        List<StudentVO> voList = studentService.listStudent();
        return ResultBean.ok(voList);
    }

}
