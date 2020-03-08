package org.windlike.util.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import oracle.jdbc.OracleTypes;

/**
 * 频繁创建销毁创建消耗...数据库的连接 消耗数据库资源 》》》数据库连接池，连接复用
 * sql语句、参数、结果集获取、结果集遍历不便后续维护，硬编码！》》》mybatis
 */

//JDBC开发步骤：
/*  1.导入驱动包；
    2.加载驱动；
    3.获取连接；
    4.获取执行sql的preparedstatment；
    5.封装参数；
    6.执行sql；
    7.获取结果；
    8.释放资源。
*/
public class TestJdbc {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        // 加载驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // 获取连接
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:mytest";
        String user = "SCOTT";
        String password = "TIGER";
        Connection conn = DriverManager.getConnection(url, user, password);
        // 获取执行SQL的statement
        // java.sql.CallableStatement 执行SQL存储过程的接口
        String sql = "{call proc_getAnnualSal(?,?)}";
        PreparedStatement ps = conn.prepareStatement(sql);
        CallableStatement cs = conn.prepareCall(sql);
        // 设置入参
        int empno = 7654;
        cs.setInt(1, empno);
        // 注册出参
        cs.registerOutParameter(2, OracleTypes.NUMBER);
        // 执行SQL
        cs.execute();
        // 获取出参结果
        int annualSal = cs.getInt(2);
        System.out.println("员工" + empno + "的年薪："+annualSal);
        // 释放资源
        cs.close();
        conn.close();
    }
}

/*
try {
    // 加载...
    // ...
    // 遍历结果集
    while(rs.next()){}
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if(rs!=null)
    try{
        rs.close();
    }catch(Exception){}
}
 */
