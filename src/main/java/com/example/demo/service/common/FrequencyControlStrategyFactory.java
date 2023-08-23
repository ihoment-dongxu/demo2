package com.example.demo.service.common;

import com.example.demo.pojo.common.FrequencyControlDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流策略工厂
 *
 * @author dongxu
 * @create 2023-08-22 下午9:54
 */
public class FrequencyControlStrategyFactory {
    /**
     * 指定时间内总次数限流
     */
    public static final String TOTAL_COUNT_WITH_IN_FIX_TIME_FREQUENCY_CONTROLLER = "TotalCountWithInFixTime";

    /**
     * 限流策略
     */
    static Map<String, AbstractFrequencyControlService<?>> frequencyControlServiceStrategyMap = new ConcurrentHashMap<>(8);

    /**
     * 将策略类放入工厂
     *
     * @param strategyName            策略名称
     * @param frequencyControlService 策略类
     */
    public static <K extends FrequencyControlDTO> void registerFrequencyController(String strategyName, AbstractFrequencyControlService<K> frequencyControlService) {
        frequencyControlServiceStrategyMap.put(strategyName, frequencyControlService);
    }

    /**
     * 根据名称获取策略类
     *
     * @param strategyName 策略名称
     * @return 对应的限流策略类
     */
    @SuppressWarnings("unchecked")
    public static <K extends FrequencyControlDTO> AbstractFrequencyControlService<K> getFrequencyControllerByName(String strategyName) {
        return (AbstractFrequencyControlService<K>) frequencyControlServiceStrategyMap.get(strategyName);
    }

    /**
     * 构造器私有
     */
    private FrequencyControlStrategyFactory() {

    }
}
