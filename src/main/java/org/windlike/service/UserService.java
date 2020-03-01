package org.windlike.service;

import org.windlike.entity.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> queryUsers();
}
