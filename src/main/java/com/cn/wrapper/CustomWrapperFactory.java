package com.cn.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * @description: 作用，如果resultType是map类型的话，就可以看到key已经是驼峰式而不是columnName了。
 * @author: helisen
 * @create: 2021-02-19 15:57
 **/
public class CustomWrapperFactory implements ObjectWrapperFactory {
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new CamelMapWrapper(metaObject, (Map)object);
    }
}
