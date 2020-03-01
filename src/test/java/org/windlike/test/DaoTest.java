package org.windlike.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.windlike.dao.UserDao;
import org.windlike.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DaoTest {

    @Test
    public void addUsersTest() throws IOException {
        // 1.加载mybatis配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建sqlSession工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        // 3.创建sqlSession对象
        SqlSession session = factory.openSession();
        // 4.获取代理对象
        UserDao dao = session.getMapper(UserDao.class);
        // 5.调用方法
        User user = new User();
        user.setName("范闲");
        user.setAge(20);
        dao.addUser(user);
        // 6.提交事务
        session.commit();
        // 7.释放资源
        session.close();
        is.close();
    }

    @Test
    public void queryUsersTest() throws IOException {
        // 1.加载mybatis配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建sqlSession工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        // 3.创建sqlSession对象
        SqlSession session = factory.openSession();
        // 4.获取代理对象
        UserDao dao = session.getMapper(UserDao.class);
        // 5.调用方法
        List<User> users = dao.queryUsers();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        session.close();
        is.close();
    }
}
