package com.example.demo.utils;

import com.example.demo.pojo.common.DingMessage;
import com.example.demo.singleton.RestTemplateSingleton;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 钉钉消息工具类
 *
 * @author dongxu
 */
public class DingTalkUtil {

    private static final String TEST_CONTENT_PREFIX = "##### <font color=#FF0000 size=6 face='黑体'>代码测试请忽略: </font>\n";

    private static final String API_DING_ALARM_CONTENT = "<font color=#FF0000 size=6 face='黑体'>事故等级: High error</font>\n" +
            "##### **Item**: ${env}-500 \n" +
            "##### **Api**: ${request.originalUrl} \n" +
            ">##### **Header**: \n" +
            "${request.header} \n" +
            ">##### **Body**: \n" +
            "${rawBody} \n" +
            "##### **Error**: \n" +
            "${stack}";

    private static final String DING_ALARM_SECRET = "SEC20fd1145a39b2562ebdecb328811af621e00837322a67066e0a44f4e25b57bd5";

    private static final String DING_ALARM_URL = "https://oapi.dingtalk.com/robot/send?access_token=5ffe1e801cb56262a34f43cc9d38e8eedead1f293ec08c7d53a38caf8c77d4ed&timestamp={timestamp}&sign={sign}";

    /**
     * 钉钉告警
     *
     * @param env           环境
     * @param requestUri    请求地址
     * @param requestHeader 请求头
     * @param requestParam  请求参数
     * @param stackTrace    堆栈信息
     */
    public static void sendDingAlarm(String env, String requestUri, String requestHeader, String requestParam, String stackTrace) {
        Long timestamp = System.currentTimeMillis();
        try {
            String stringToSign = timestamp + "\n" + DING_ALARM_SECRET;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(DING_ALARM_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(org.apache.commons.codec.binary.Base64.encodeBase64(signData)), "UTF-8");
            String messageContent = API_DING_ALARM_CONTENT
                    .replace("${env}", env)
                    .replace("${request.originalUrl}", requestUri)
                    .replace("${request.header}", requestHeader)
                    .replace("${rawBody}", requestParam)
                    .replace("${stack}", stackTrace);
            if (!isProduction(env)) {
                messageContent = TEST_CONTENT_PREFIX + messageContent;
            }
            DingMessage dingMessage = new DingMessage("App商城请求异常", messageContent, false);
            String body = JSONUtil.toJSON(dingMessage);
            RestTemplate restTemplate = RestTemplateSingleton.SINGLETON.getInstance();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);
            String url = DING_ALARM_URL
                    .replace("{timestamp}", String.valueOf(timestamp))
                    .replace("{sign}", sign);
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isProduction(String env) {
        return "pro".equals(env) || "pre".equals(env);
    }

    /**
     * 钉钉告警
     *
     * @param dingMessage 钉钉消息
     */
    public static void sendDingAlarm(DingMessage dingMessage) {
        Long timestamp = System.currentTimeMillis();
        try {

            String stringToSign = timestamp + "\n" + DING_ALARM_SECRET;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(DING_ALARM_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");

            String body = new Gson().toJson(dingMessage);

            RestTemplate restTemplate = RestTemplateSingleton.SINGLETON.getInstance();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

            String url = DING_ALARM_URL
                    .replace("{timestamp}", String.valueOf(timestamp))
                    .replace("{sign}", sign);
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String messageContent = "测试消息内容，测试消息内容，测试消息内容";
        DingMessage dingMessage = new DingMessage("测试钉钉消息", messageContent, true);
        sendDingAlarm(dingMessage);
    }
}
