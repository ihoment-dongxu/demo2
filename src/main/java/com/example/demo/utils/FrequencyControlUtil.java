package com.example.demo.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.common.FrequencyControlDTO;
import com.example.demo.service.common.AbstractFrequencyControlService;
import com.example.demo.service.common.FrequencyControlStrategyFactory;

import java.util.List;

/**
 * 限流工具类 提供编程式的限流调用方法
 *
 * @author dongxu
 * @create 2023-08-22 下午9:49
 */
public class FrequencyControlUtil {


    /**
     * 单限流策略的调用方法-编程式调用
     *
     * @param strategyName     策略名称
     * @param frequencyControl 单个频控对象
     * @param supplier         服务提供着
     * @return 业务方法执行结果
     * @throws Throwable
     */
    public static <T, K extends FrequencyControlDTO> T executeWithFrequencyControl(String strategyName, K frequencyControl, AbstractFrequencyControlService.SupplierThrowWithoutParam<T> supplier) throws Throwable {
        AbstractFrequencyControlService<K> frequencyController = FrequencyControlStrategyFactory.getFrequencyControllerByName(strategyName);
        return frequencyController.executeWithFrequencyControl(frequencyControl, supplier);
    }

    public static <K extends FrequencyControlDTO> void executeWithFrequencyControl(String strategyName, K frequencyControl, AbstractFrequencyControlService.Executor executor) throws Throwable {
        AbstractFrequencyControlService<K> frequencyController = FrequencyControlStrategyFactory.getFrequencyControllerByName(strategyName);
        frequencyController.executeWithFrequencyControl(frequencyControl, () -> {
            executor.execute();
            return null;
        });
    }


    /**
     * 多限流策略的编程式调用方法调用方法
     *
     * @param strategyName         策略名称
     * @param frequencyControlList 频控列表 包含每一个频率控制的定义以及顺序
     * @param supplier             函数式入参-代表每个频控方法执行的不同的业务逻辑
     * @return 业务方法执行的返回值
     * @throws Throwable 被限流或者限流策略定义错误
     */
    public static <T, K extends FrequencyControlDTO> T executeWithFrequencyControlList(String strategyName, List<K> frequencyControlList, AbstractFrequencyControlService.SupplierThrowWithoutParam<T> supplier) throws Throwable {
        boolean existsFrequencyControlHasNullKey = frequencyControlList.stream().anyMatch(frequencyControl -> StringUtils.isEmpty(frequencyControl.getKey()));
        if (existsFrequencyControlHasNullKey) {
            throw new BusinessException("限流策略的Key字段不允许出现空值");
        }
        AbstractFrequencyControlService<K> frequencyController = FrequencyControlStrategyFactory.getFrequencyControllerByName(strategyName);
        return frequencyController.executeWithFrequencyControlList(frequencyControlList, supplier);
    }

    /**
     * 构造器私有
     */
    private FrequencyControlUtil() {

    }
}
