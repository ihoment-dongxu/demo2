package com.example.demo.pojo.request.openAi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-04-09 上午10:58
 */
@Data
@NoArgsConstructor
public class GenerateChatsRequest {
    /**
     * 使用的语言模型：gpt-3.5-turbo
     */
    private String model = "gpt-3.5-turbo";

    private List<Message> messages;

    @Data
    public static class Message {
        /**
         * 角色：'system', 'assistant', 'user'，连续对话需要传集合
         */
        public String role;

        /**
         * 文本内容
         */
        public String content;
    }
}
