package com.example.demo.service.user;

import com.example.demo.pojo.vo.school.User;
import com.example.demo.service.impl.user.UserWrapBatchService;

import java.util.List;
import java.util.Map;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:51
 */
public interface UserService {

    Map<String, User> queryUserByIdBatch(List<UserWrapBatchService.Request> userReqs);
}
