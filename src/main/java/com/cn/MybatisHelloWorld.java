package com.cn;

import com.cn.mapper.UserMapper;
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
        SqlSession session = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();
            /*User user = session.selectOne("com.cn.mapper.UserMapper.getUser", 27);
            System.out.println(user.toString());*/

            /*User u = new User("smith", 29, "男");
            int result = session.insert("com.cn.mapper.UserMapper.addUser", u);
            System.out.println("结果为：" + result);
            //新增或更新的时候需要调用commit方法，不然数据库中的数据不会更新
            session.commit();*/

            /**
             * 返回的map结果：
             * 下划线替换为驼峰之前的效果如下：
             * {19={sex=男, name=张三, created_time=2021-02-19 16:31:03.0, id=19, age=20}, 36={sex=女, name=smith2, id=36, age=29}, 26={sex=男, name=小明, id=26, age=28}, 27={sex=男, name=tom, id=27, age=5}}
             * 下划线替换为驼峰之后的效果如下：
             * {19={sex=男, name=张三, createdTime=2021-02-19 16:31:03.0, id=19, age=20}, 36={sex=女, name=smith2, id=36, age=29}, 26={sex=男, name=小明, id=26, age=28}, 27={sex=男, name=tom, id=27, age=5}}
             */
            /*返回map集合，以数据库中的列名id作为key*/
            Map<Object, Object> objectObjectMap = session.selectMap("com.cn.mapper.UserMapper.getUserMap", "id");
            System.out.println(objectObjectMap);

            User user = session.selectOne("com.cn.mapper.UserMapper.getUser", 27);
            System.out.println("第一种方式：" + user.getName() + user.getAge() + user.getSex());
            UserMapper mapper = session.getMapper(UserMapper.class);
            Map userMap = mapper.getUserMap();
            System.out.println("第二种方式：" + userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
