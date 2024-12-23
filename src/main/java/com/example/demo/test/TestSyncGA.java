package com.example.demo.test;

import com.example.demo.utils.JSONUtil;
import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dongxu
 * @create 2024-07-10 11:08
 */
public class TestSyncGA {

    private static final String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE3MjIyMjMzNDgsImlzcyI6Imlob21lbnQiLCJ1c2VySWQiOjQxMzUsInVzZXJuYW1lIjoieHVfZG9uZyJ9.W_aA56hZAno66bCMIIbinVLvXyx04SJT2Cg2w0P-apw";
    private static final String BASE_URL = "https://trade-api.govee.com";
    private static final String GA_SYNC = BASE_URL + "/rest/v1/dashboard-mall/sync";

    // 同步原始表数据
    @SneakyThrows
    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
            extracted();
//        }

    }

    private static void extracted() throws InterruptedException {
        // 输入一个时间范围
        Long startAt = 1659283200000L;
        Long endAt = 1730390400000L;
        Long syncStartAt;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        GoogleParams dto = new GoogleParams();
        do {
            long now = System.currentTimeMillis();
            // 从结束时间开始，每次减1天作为一个时间段
            syncStartAt = endAt - 10 * 24 * 60 * 60 * 1_000L;
            dto.setEndTime(endAt);
            dto.setStartTime(syncStartAt);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            System.out.println("本次执行时间段：" + sdf.format(new Date(syncStartAt)) + " 至 " + sdf.format(new Date(endAt)));
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, JSONUtil.toJSON(dto));
            Request request = new Request.Builder().url(GA_SYNC).method("POST", body).addHeader("Authorization", AUTHORIZATION).addHeader("Content-Type", "application/json").build();
            // 每次用这段时间请求同步
            try {
                Response response = client.newCall(request).execute();
                int code = response.code();
                System.out.println("response code = " + code);
            } catch (Exception e) {
                System.out.println("catch e: " + e.getMessage());
            }
            long endRequest = System.currentTimeMillis();
            // 更新结束时间
            endAt = syncStartAt;
            // 每1分钟请求一次 如果执行时间少于30秒，则不休眠继续执行下一次
            if (endRequest - now > 30 * 60 * 1000L) {
                System.out.println("sleep 1 minute");
                Thread.sleep(60 * 1000L);
                System.out.println("sleep end");
            } else {
//                Thread.sleep(10 * 1000L);
//                System.out.println("执行时间过短，休眠10秒继续执行下一次");
                System.out.println("执行时间过短，继续执行下一次");
            }

        } while (endAt >= startAt);

        System.out.println("end--------------------------------");
    }


    @Data
    public static class GoogleParams {
        private Long startTime;

        private Long endTime;
    }

}
