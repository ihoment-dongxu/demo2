package com.example.demo.test;

import com.example.demo.utils.JSONUtil;
import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author dongxu
 * @create 2024-07-10 11:08
 */
public class TestSyncTargetFee {

    private static final String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE3MjIyMjMzNDgsImlzcyI6Imlob21lbnQiLCJ1c2VySWQiOjQxMzUsInVzZXJuYW1lIjoieHVfZG9uZyJ9.W_aA56hZAno66bCMIIbinVLvXyx04SJT2Cg2w0P-apw";
    private static final String BASE_URL = "https://backend.lanjingerp.com/trade/rest";
    private static final String SHOPIFY_SYNC_URL = BASE_URL + "/v1/shopify-deliver-order-status/fee/sync";
    private static final String OUTBOUND_SYNC_URL = BASE_URL + "/v1/oversea-house/outbound-fee/sync";
    private static final String TIKTOK_SYNC_URL = BASE_URL + "/v1/tiktok-delivery-package/fee/sync";
    private static final String MALL_SYNC_URL = "https://mall-admin.api.govee.com/app-mall-backend/v1/delivery-order/schedule/synWeight";

    @SneakyThrows
    public static void main(String[] args) {
        // 输入一个时间范围
        Long startAt = 1672502400000L;
        Long endAt = 1722571880575L;
        Long syncStartAt;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ShopifyDeliveryOrderShippingFeeSyncDTO dto = new ShopifyDeliveryOrderShippingFeeSyncDTO();
        dto.setWarehouseList(Arrays.asList("GOODCANG","FBA","WINIT"));
        dto.setStatusList(Arrays.asList(5, 7, 8));
        do {
            long now = System.currentTimeMillis();
            // 从结束时间开始，每次减1天作为一个时间段
            syncStartAt = endAt - 5 * 24 * 60 * 60 * 1_000L;
            dto.setEndTime(endAt);
            dto.setStartTime(syncStartAt);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            System.out.println("本次执行时间段：" + sdf.format(new Date(syncStartAt)) + " 至 " + sdf.format(new Date(endAt)));
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, JSONUtil.toJSON(dto));
            Request request = new Request.Builder().url(SHOPIFY_SYNC_URL).method("POST", body).addHeader("Authorization", AUTHORIZATION).addHeader("Content-Type", "application/json").build();
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
                System.out.println("执行时间过短，无需休眠继续执行下一次");
            }

        } while (endAt >= startAt);

        System.out.println("end--------------------------------");
    }

    @Data
    public static class OverseaWarehouseOutboundFeeSyncDTO {
        /**
         * 海外仓类型 GOODCNAG / WINIT
         */
        private List<String> warehouseArr;

        private Long startAt;

        private Long endAt;

    }

    @Data
    public static class ShopifyDeliveryOrderShippingFeeSyncDTO {

        /**
         * 海外仓
         */
        private List<String> warehouseList;

        private List<Integer> statusList;

        private Long startTime;

        private Long endTime;

    }


}
