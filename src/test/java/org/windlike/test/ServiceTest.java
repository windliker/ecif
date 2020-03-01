package org.windlike.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.windlike.service.UserService;

public class ServiceTest {

    @Test
    public void testSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.addUser(null);
        userService.queryUsers();
    }
}
