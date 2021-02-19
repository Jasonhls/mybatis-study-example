package com.cn.mapper;

import com.cn.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: helisen
 * @create: 2021-01-28 16:02
 **/
public interface UserMapper {

    User getUser(Integer id);
    void addUser(@Param("user") User user);
}
