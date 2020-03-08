# ecif

#### 介绍

#### 软件架构
Spring+SpringMVC+Mybatis
1. Spring是整个项目中装配bean的大工厂，在配置文件中可以指定使用特定的参数去调用实体类的构造方法来实例化对象。
Spring的核心思想是控制反转(IoC),即不再需要程序员显式地new来获得对象,而是让Spring来实例化对象。
Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。

2. SpringMVC在项目中拦截用户的请求，它的核心servlet——DispatcherServlet承担中介或是前台这样的职责，
将用户的请求通过HandlerMapping来匹配Controller，Controller负责对用户的请求做相应的处理。
SpringMVC分离了控制器、模型对象、分派器以及处理程序对象的角色，这种分离让它们更容易进行定制。
SpringMVC相当于Spring+Struts2+Hibernate中的Struts2.

3. Mybatis是对jdbc的封装，使得对数据库底层的操作变得透明。mybatis所有操作都是围绕一个sqlSessionFactory实例展开的。
Mybatis通过配置文件关联到各实体类的Mapper文件，Mapper文件中配置了每个类对数据库所进行的SQL语句映射。
每次与数据库交互时，通过sqlSessionFactory获得一个sqlSession，再用sqlSession执行sql语句。
Mybatis使用简单的XML或注解用于配置和映射，将接口和Java的POJO映射成数据库中的记录。

4. 整体流程：页面发送请求给控制器，控制器调用业务层处理逻辑，逻辑层向持久层发送请求，持久层与数据库交互并将结果返回给业务层，
业务层将处理结果发送给控制器，控制器再调用视图向前端展示数据。

5. 项目管理工具:Maven
maven项目中的目录结构分为Sources,Tests,Resources,Test Resources,Excluded五种.

6. 版本控制系统:Git

7. 代码托管服务:GitHub,Gitee

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
