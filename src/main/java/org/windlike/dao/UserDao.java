package org.windlike.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.windlike.entity.User;

import java.util.List;

public interface UserDao {

    @Insert("insert into user(name, age) values(#{name}, #{age})")
    public void addUser(User user);

    @Select("select * from user")
    public List<User> queryUsers();
}
