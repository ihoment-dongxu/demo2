package com.example.demo.controller;

import com.example.demo.component.OpenAi;
import com.example.demo.pojo.request.openAi.GenerateChatsRequest;
import com.example.demo.pojo.request.openAi.GenerateImagesRequest;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;

/**
 * @author dongxu
 * @create 2023-04-09 上午10:57
 */
@RestController
@RequestMapping(value = "/openAi")
public class OpenAiController {

    @Resource
    private OpenAi openAi;

    @ApiModelProperty(value = "根据提示创建图像")
    @PostMapping("/generateImages")
    public String generateImages(@RequestBody GenerateImagesRequest request) {
        return openAi.generateImages(request.getPrompt(), request.getTemperature(), request.getMaxTokens(), request.getStop(),
                request.getLogprobs(), request.isEcho(), request.getN());
    }

    /**
     * value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，
     * 我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，
     * 如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     *
     * @param request
     * @return
     */
    @ApiModelProperty(value = "根据提示创建对话")
    @PostMapping("/generateChats")
    @Retryable(value = Exception.class, exclude = {SocketTimeoutException.class}, maxAttempts = 2, backoff = @Backoff(delay = 1000, multiplier = 2))
    public String generateChats(@RequestBody GenerateChatsRequest request) {
        return openAi.generateChats(request);
    }

    /**
     * 如果一个类中有多个方法需要重试，如何区分@Recover？
     * 可以根据异常类型区分，异常可以在@Retryable的value,include,exclude中操作指定
     * 如果异常也相同呢？
     * 可以根据入参类型区分，
     * 需要注意的是，@Retryable方法和@Recover方法中的参数类型和数量必须相同，否则Spring框架将无法确定哪个方法抛出了异常。
     * @Recover
     * public void recover(Exception e, Object... args) {
     * if (args[0] instanceof String) {
     * // Recovery logic for methodA() goes here
     * } else if (args[0] instanceof Integer) {
     * // Recovery logic for methodB() goes here
     * }
     * }
     */
    @Recover
    public String recover(Exception e) {
        System.out.println("recovered");
        System.out.println(e.getMessage());
        return "recovered";
    }
}
