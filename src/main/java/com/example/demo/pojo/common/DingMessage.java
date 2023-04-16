package com.example.demo.pojo.common;

import lombok.Data;

import java.util.List;

/**
 * 钉钉请求体
 *
 * @author dongxu
 */
@Data
public class DingMessage {

    /**
     * 消息类型
     */
    private String msgtype;
    /**
     * 内容
     */
    private Text text;
    /**
     * markdown格式文本
     */
    private Markdown markdown;
    /**
     * 是否@全体
     */
    private At at;

    @Data
    static class Text {
        private String content;
    }

    @Data
    static class Markdown {
        private String title;
        private String text;
    }

    @Data
    static class At {
        private List<String> atMobiles;
        private Boolean isAtAll;
    }

    public DingMessage() {
    }

    public DingMessage(String title, String textContent, boolean isAtAll) {
        this.msgtype = "markdown";
        Markdown markdown = new Markdown();
        markdown.setTitle(title);
        markdown.setText(textContent);
        this.markdown = markdown;
        At at = new At();
        at.isAtAll = isAtAll;
        this.at = at;
    }

    public DingMessage(String content, boolean isAtAll) {
        this.msgtype = "text";
        Text text = new Text();
        text.setContent(content);
        this.text = text;
        At at = new At();
        at.isAtAll = isAtAll;
        this.at = at;
    }

}
