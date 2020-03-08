package org.windlike.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.windlike.entity.User;

import java.util.List;

@Repository
public interface UserDao {

    @Insert("insert into user(id, username, age) values(#{id}, #{username}, #{age})")
    public void addUser(User user);

    @Select("select * from user")
    public List<User> queryUsers();
}
