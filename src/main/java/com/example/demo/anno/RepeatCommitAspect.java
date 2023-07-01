package com.example.demo.anno;

import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.result.ResultBean;
import com.example.demo.utils.HmacUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 重复提交注解切面
 *
 * @author dongxu
 * @create 2023-07-01 下午4:07
 */
@Aspect
public class RepeatCommitAspect {

    private static final ThreadLocal<String> KEY_CACHE = new ThreadLocal<>();

    /**
     * Redis key 头部
     */
    public static final String REPEAT_COMMIT_KEY = "repeat_commit:";
    /**
     * 加密的密钥
     */
    public static final String REPEAT_COMMIT_SECRET_KEY = "repeatCommit";

    @Before("@annotation(repeatCommit)")
    private void doBefore(JoinPoint joinPoint, RepeatCommit repeatCommit) {

        long interval = 0L;

        // 如果interval大于0
        if (repeatCommit.interval() > 0) {
            interval = repeatCommit.timeUnit().toMillis(repeatCommit.interval());
        }

        if (interval < 1000L) {
            throw new BusinessException("重复提交间隔时间设置不能小于1秒");
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return;
        }

        HttpServletRequest request = attributes.getRequest();

        String requestURI = request.getRequestURI();

        String argStr = Arrays.toString(joinPoint.getArgs());

        // todo 模拟获取用户ID 没有用户ID则结束
        Integer userId = (int) interval;

        if (userId == null) {
            return;
        }

        // uri + 用户ID + 请求参数 加盐
        String key = REPEAT_COMMIT_KEY + requestURI + userId + argStr;

        String encrypt = HmacUtil.encrypt(key, REPEAT_COMMIT_SECRET_KEY, HmacUtil.HmacEnum.HmacSHA1.name());

        KEY_CACHE.set(encrypt);

        // todo 记录日志
        // todo 检查Redis 是否存在该key
        // 存在返回错误信息
        // 不存在则将encrypt放入redis

    }

    @AfterReturning(pointcut = "@annotation(repeatCommit)", returning = "jsonResult")
    private void doAfterReturning(JoinPoint joinPoint, RepeatCommit repeatCommit, Object jsonResult) {
        if (jsonResult instanceof ResultBean) {
            try {
                ResultBean resultBean = (ResultBean) jsonResult;
                boolean success = resultBean.isSuccess();
                if (success) {
                    // 删除RedisKey
                }
            } catch (Exception e) {
                // todo 告警
            } finally {
                // 删除本地key
                KEY_CACHE.remove();
            }
        }
    }
}
