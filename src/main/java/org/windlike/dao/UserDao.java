package org.windlike.dao;

import org.windlike.entity.User;

import java.util.List;

public interface UserDao {

    public void addUser(User user);

    public List<User> queryUsers();
}
