package com.example.demo.component;

import com.example.demo.pojo.request.openAi.GenerateChatsRequest;
import com.example.demo.singleton.RestTemplateSingleton;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * openAi
 *
 * @author dongxu
 * @create 2023-04-09 上午10:53
 * 接入文档：https://mp.weixin.qq.com/s/JPG3FPJoTyq1TvUzzpU8HA
 * 官方地址：https://platform.openai.com/docs/api-reference/images/create
 */
@Component
public class OpenAi {
    /**
     * 生成图像URL
     */
    private static final String OPENAI_GENERATE_IMAGES_URL = "https://api.openai.com/v1/images/generations";
    /**
     * 生成对话URL
     */
    private static final String OPENAI_GENERATE_CHATS_URL = "https://api.openai.com/v1/chat/completions";

    @Value(value = "${open-ai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = RestTemplateSingleton.SINGLETON.getInstance();

    /**
     * 图片生成
     */
    public String generateImages(String prompt, float temperature, int maxTokens, String stop, final int logprobs, final boolean echo, int n) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        // We are including only some of the parameters to the json request
        String requestJson = "{\"prompt\":\"" + prompt + "\",\"n\":" + n + "}";
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_GENERATE_IMAGES_URL, request, String.class);
        return response.getBody();
    }

    /**
     * 对话生成
     */
    public String generateChats(GenerateChatsRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        // We are including only some of the parameters to the json request
        String requestJson = new Gson().toJson(chatRequest);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_GENERATE_CHATS_URL, request, String.class);
        return response.getBody();
    }
}
