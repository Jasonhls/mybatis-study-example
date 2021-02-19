package com.cn.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: 自定义Java类型与数据库类型的转换器
 * 方法：
 * 第一步：继承BaseTypeHandler，重写方法
 * 第二步：在mybatis-config.xml文件中注册
 *<typeHandlers>
 *         <typeHandler handler="com.cn.handler.MyTypeHandler"/>
 *</typeHandlers>
 * 第三步：在我们需要使用的字段上指定，比如：插入值的时候，从Java类型到JDBC类型，在字段属性中指定typeHandler:
 * <insert id="insertBlog" parameterType="com.wuzz.domain.Blog">
 * 　　insert into blog (bid, name, author_id)
 * 　　values (#{bid,jdbcType=INTEGER},
 * 　　#{name,jdbcType=VARCHAR,typeHandler=com.wuzz.type.MyTypeHandler},
 * 　　#{authorId,jdbcType=INTEGER})
 * </insert>
 * 返回值的时候，从JDBC类型到Java类型，在resultMap的列上指定typeHandler：
 * <result column="name" property="name" jdbcType="VARCHAR" typeHandler="com.cn.handler.MyTypeHandler"/>
 *
 *
 * 处理枚举类型
 * 　　若想映射枚举类型 Enum，则需要从 EnumTypeHandler 或者 EnumOrdinalTypeHandler 中选一个来使用。
 * 　　比如说我们想存储取近似值时用到的舍入模式。默认情况下，MyBatis 会利用 EnumTypeHandler 来把 Enum 值转换成对应的名字。
 * 　　注意 EnumTypeHandler 在某种意义上来说是比较特别的，其他的处理器只针对某个特定的类，而它不同，它会处理任意继承了 Enum 的类。
 * 　　不过，我们可能不想存储名字，相反我们的 DBA 会坚持使用整形值代码。那也一样轻而易举： 在配置文件中把 EnumOrdinalTypeHandler 加到 typeHandlers 中即可， 这样每个 RoundingMode 将通过他们的序数值来映射成对应的整形数值。
 * @author: helisen
 * @create: 2021-02-19 12:40
 **/
public class MyTypeHandler extends BaseTypeHandler<String> {
    /**
     * 从Java类型到JDBC类型，设置非空参数
     * @param preparedStatement
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        //设置String类型的参数的时候调用，Java类型到JDBC类型
        //注意只有在字段上添加typeHandler属性才会生效
        System.out.println("------------setNonNullParameter：" + parameter);
        preparedStatement.setString(i, parameter);
    }

    /**
     * 从JDBC类型到Java类型，获取空结果集（根据列名），一般都是调用这个
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        //根据列名获取String类型的参数的时候调用，JDBC类型到Java类型
        //注意只有在字段上添加typeHandler属性才会生效
        System.out.println("------------getNullableResult1：" + columnName);
        return resultSet.getString(columnName);
    }

    /**
     * 获取空结果集（根据下标值）
     * @param resultSet
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        System.out.println("--------------getNullableResult2：" + columnIndex);
        return resultSet.getString(columnIndex);
    }

    /**
     * 存储过程用的
     * @param callableStatement
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        System.out.println("---------------------getNullableResult3：" + columnIndex);
        return callableStatement.getString(columnIndex);
    }
}
