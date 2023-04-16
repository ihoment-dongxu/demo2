package com.example.demo.pojo.request.openAi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongxu
 * @create 2023-04-09 上午10:58
 */
@Data
@NoArgsConstructor
public class GenerateImagesRequest {
    /**
     * 给api的提示，最大1000字符
     */
    private String prompt;
    private float temperature;
    private int maxTokens;
    private String stop;
    private int logprobs;
    private boolean echo;
    /**
     * 要生成的图像数，1-10,默认1
     */
    private int n;
}
