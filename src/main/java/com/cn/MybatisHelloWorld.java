package com.cn;

import com.cn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

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

            /*返回map集合，以数据库中的列名id作为key*/
            /**
             * 返回的map结果：
             * 下划线替换为驼峰之前的效果如下：
             * {19={created_time=2021-02-19 16:31:03.0, sex=男, name=张三, id=19, age=20}, 20={sex=男, name=张三, id=20, age=20}, 21={sex=男, name=张三, id=21, age=20}, 22={sex=男, name=张三, id=22, age=20}, 23={sex=男, name=张三, id=23, age=20}, 24={sex=男, name=张三, id=24, age=20}, 25={sex=男, name=小明, id=25, age=28}, 26={sex=男, name=小明, id=26, age=28}, 27={sex=男, name=tom, id=27, age=5}}
             * 下划线替换为驼峰之后的效果如下：
             * {19={createdTime=2021-02-19 16:31:03.0, sex=男, name=张三, id=19, age=20}, 20={sex=男, name=张三, id=20, age=20}, 21={sex=男, name=张三, id=21, age=20}, 22={sex=男, name=张三, id=22, age=20}, 23={sex=男, name=张三, id=23, age=20}, 24={sex=男, name=张三, id=24, age=20}, 25={sex=男, name=小明, id=25, age=28}, 26={sex=男, name=小明, id=26, age=28}, 27={sex=男, name=tom, id=27, age=5}}
             */
            Map<Object, Object> objectObjectMap = session.selectMap("com.cn.mapper.UserMapper.getUserMap", "id");
            System.out.println(objectObjectMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
