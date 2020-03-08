package org.windlike.util.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory = builder.build(inputStream);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
