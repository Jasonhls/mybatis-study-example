package com.cn;

import com.cn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @description:
 * @author: helisen
 * @create: 2021-01-28 15:48
 **/
public class MybatisHelloWorld {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            User user = session.selectOne("com.cn.mapper.UserMapper.getUser", 19);
            System.out.println(user.toString());

            User u = new User("smith", 29, "男");
            int result = session.insert("com.cn.mapper.UserMapper.addUser", u);
            System.out.println("结果为：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
