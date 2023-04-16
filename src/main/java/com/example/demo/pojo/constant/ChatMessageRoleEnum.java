package com.example.demo.pojo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dongxu
 * @create 2023-04-11 上午10:01
 */
@Getter
@AllArgsConstructor
public enum ChatMessageRoleEnum {
    /**
     * Summarizer: 可以将我提供的文字摘要成列点文字给我。
     *
     * Challenger: 可以根据我提供的文字提出问题。
     *
     * Creator: 可以针对我提供的主题提出相关的子议题。
     *
     * Editor: 可以将我提供的文字编辑成更通顺的版本。
     *
     * Explainer: 可以将我提供的文字去除掉艰深的用字与术语，转化成最简单的说明版本给我。
     */
    SYSTEM("system", "定义助手的行为方式，例如提示或规则。"),
    ASSISTANT("assistant", "填写对话历史和必要信息，让 ChatGPT 在对话中使用这些信息"),
    USER("user", "填写用户的指令，让 ChatGPT 知道用户想要 ChatGPT 做什么，以及如何做");
    private String role;
    private String desc;
}
