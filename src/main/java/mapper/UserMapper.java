package mapper;

import com.cn.pojo.User;

import java.util.Map;

/**
 * @description:
 * @author: helisen
 * @create: 2021-01-28 16:02
 **/
public interface UserMapper {

    User getUser(Integer id);

    void addUser(User user);

    Map getUserMap();
}
