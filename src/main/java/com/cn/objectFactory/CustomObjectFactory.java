package com.cn.objectFactory;

import com.cn.pojo.User;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * @description: 我们希望给User对象的属性createdUser设置默认值
 * @author: helisen
 * @create: 2021-02-19 16:53
 **/
public class CustomObjectFactory extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {
        T result = super.create(type);
        if(type.equals(User.class)) {
            ((User)result).setCreatedUser("SYSTEM");
        }
        return result;
    }
}
