package org.windlike.service.impl;

import org.springframework.stereotype.Service;
import org.windlike.entity.User;
import org.windlike.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {
        System.out.println("service插入数据");
    }

    @Override
    public List<User> queryUsers() {
        System.out.println("service查询一组数据");
        return null;
    }
}
