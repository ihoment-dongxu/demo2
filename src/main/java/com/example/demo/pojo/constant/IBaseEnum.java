package com.example.demo.pojo.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author dongxu
 * @create 2023-11-05 上午10:32
 */
public interface IBaseEnum<T> {

    /**
     * 初始化
     *
     * @param code code
     * @param msg  message
     */
    default void initEnum(T code, String msg) {
        EnumContainer.putEnum(this, code, msg);
    }

    default T getCode() {
        return EnumContainer.getEnum(this).code;
    }

    default String getMsg() {
        return EnumContainer.getEnum(this).text;
    }

    @SuppressWarnings("unchecked")
    static <T, R extends IBaseEnum<T>> R getEnumByCode(Class<? extends IBaseEnum<T>> calzz, T code) {
        return Stream.of(calzz.getEnumConstants())
                .filter(x -> x.getCode().equals(code))
                .map(v -> (R) v)
                .findAny()
                .orElse(null);
    }

    class EnumContainer {
        private static final Map<IBaseEnum, EnumBean> ENUM_MAP = new ConcurrentHashMap<>();

        public static <T> void putEnum(IBaseEnum tiBaseEnum, T code, String msg) {
            ENUM_MAP.put(tiBaseEnum, new EnumBean(code, msg));
        }

        static <K extends IBaseEnum<T>, T> EnumBean<T> getEnum(K dict) {
            return ENUM_MAP.get(dict);
        }
    }

    class EnumBean<T> {

        private final T code;
        private final String text;

        public EnumBean(T code, String text) {
            this.code = code;
            this.text = text;
        }

        @Override
        public String toString() {
            return "EnumBean{" +
                    "code=" + code +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
