package com.example.demo.handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author DX
 */
@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    /**
     * 和客户端链接成功的时候触发该方法
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("和客户端建立连接");
    }

    /**
     * 和客户端建立连接后，处理客户端发送的请求
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 收到消息
        String payload = message.getPayload();
        log.info("收到消息:{}", payload);
        // 发送给客户端
        session.sendMessage(new TextMessage(fakeAi(payload)));
        // 关闭连接
        session.close();
    }

    /**
     * 和客户端连接失败的时候触发该方法
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("出现异常");
    }

    /**
     * 和客户端断开连接的时候触发该方法
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("和客户端断开连接");
    }

    private static String fakeAi(String input) {
        if (input == null || "".equals(input)) {
            return "你说什么？没听清︎";
        }
        return input.replace('你', '我')
                .replace("吗", "")
                .replace('?', '!')
                .replace('？', '！');
    }
}
