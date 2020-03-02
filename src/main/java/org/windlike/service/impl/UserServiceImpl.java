package org.windlike.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.windlike.dao.UserDao;
import org.windlike.entity.User;
import org.windlike.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        System.out.println("service插入数据");
        userDao.addUser(user);
    }

    @Override
    public List<User> queryUsers() {
        System.out.println("service查询一组数据");
        return userDao.queryUsers();
    }
}
