package com.example.demo.pojo.constant;

/**
 * @author dongxu
 * @create 2023-11-05 上午11:18
 */
public enum EmailTypeEnum implements IBaseEnum<Integer> {
    PAY(1, "支付成功","tag1"),
    CANCEL(2, "取消订单","tag2"),
    ;

    private final String tag;


    EmailTypeEnum(Integer code, String msg, String tag) {
        initEnum(code, msg);
        this.tag = tag;
    }

    public static void main(String[] args) {
        Integer code = EmailTypeEnum.PAY.getCode();
        System.out.println(code);
    }
}
