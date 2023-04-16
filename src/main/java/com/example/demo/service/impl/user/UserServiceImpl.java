package com.example.demo.service.impl.user;

import com.example.demo.pojo.vo.school.User;
import com.example.demo.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Map<String, User> queryUserByIdBatch(List<UserWrapBatchService.Request> userReqs) {
        // 全部参数
        List<Long> userIds = userReqs.stream().map(UserWrapBatchService.Request::getUserId).collect(Collectors.toList());
        // 模拟查询
        Map<String, User> userMap = new HashMap<>(userIds.size());
        for (Long userId : userIds) {
            userMap.put(userId.toString(), new User(userId));
        }
        return userMap;
    }

}
