package org.windlike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.windlike.entity.User;
import org.windlike.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 3种依赖注入方式：
     *  1.字段(属性)注入： 不要使用。
     *    1.1 利用反射获得依赖进而实例化。类与依赖容器强耦合，没有启动整个DI容器时，
     *    在反射时无法提供这个类需要的依赖，这个类就无法创建实例。
     *    1.2 实际的依赖隐藏在外面，不是在构造方法或其他方法里反射的。
     *    1.3 不能向final字段注入依赖。
     *  2.构造注入：强制依赖，强制注入，不可变final，推荐使用。
     *  3.setter注入：选择依赖，选择注入，可变性，不保证或不需要成功注入对象，即可能注入null。
     *
     *  tip:
     *    1. 依赖注入的核心思想之一就是 【被容器管理的类，托管类】不应该依赖于所使用的的DI容器，它应该只是一个普通的POJO，
     *    在将所有必须的依赖传递给它后，它是可以独立被实例化的，如可以在单元测试中示例化这个类，而无需启动DI容器。
     *    集成测试才需要启动DI容器。使用属性注入的方式是不能保证这一点的。如果没有DI容器耦合，不管有没有被DI容器管理，
     *    都可以实例化这个类，甚至切换到新的DI框架，即可以脱离spring的管理去操作这个类。
     *    2. 使用new调用默认构造方法创建某个类的对象时，如果这个对象缺少必要的依赖，调用的时候就会出现空指针异常。
     *    这样的类不能在DI容器之外（如测试、其他模块）使用。缺少必要的依赖是因为这个类使用属性注入方式获得依赖，
     *    除了反射之外，没有别的方式能够向它提供所需依赖项。
     *    举个例子
     *    public class DependencyA {public void print(){System.out.println("dependencyA");}}
     *    public class POJO {// 无法获得对象根源：属性注入!!!
     *      @Autowired private DependencyA dependencyA;public void execute(){dependencyA.print();}}
     *    public class Test {public static void main(String[] args){POJO pojo = new POJO();pojo.execute();}}
     *    当执行execute()方法时就会报空指针异常，因为属性注入方式不能在DI容器之外获得对象，DependencyA的实例没有被创建。
     *    3. 既然使用了依赖注入方式，那么就表明这个类不再对这些依赖负责，获取依赖的职责交给DI依赖注入容器来管理来装配。
     *    当类不再负责获取依赖时，它只要清楚本身需要什么依赖，以及哪些是必须的，哪些是可选的，从而正确地选择使用构造注入或setter注入。
     *    4. 一个类有很多依赖不好，表示这个类做的事很多，违背单一职责原则。
     *
     *  summary:
     *    Always use constructor based dependency injection in your beans.
     *    Always use assertions for mandatory dependencies
     */
    /* 属性注入 最好不要使用
    @Autowired
    private UserService userService;
    */
    /* setter注入
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    */

    /**
     * 推荐使用构造注入
     *     private final DependencyA dependencyA;
     *     private final DependencyB dependencyB;
     *
     *     @Autowired
     *     public Controller(DependencyA dependencyA, DependencyB dependencyB) {
     *         this.dependencyA = dependencyA;
     *         this.dependencyB = dependencyB;
     *     }
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/queryUsers")
    public String queryUsers(Model model) {
        System.out.println("controller查询一组数据");
        // 使用注入的userService对象
        List<User> users = userService.queryUsers();
        model.addAttribute("users", users);
        return "success";
    }

    @RequestMapping("/addUser")
    public String addUser(User user) {
        System.out.println("controller新增一条数据");
        userService.addUser(user);
        return "success";
    }
}
