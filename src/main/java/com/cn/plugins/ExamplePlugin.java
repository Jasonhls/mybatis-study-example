package com.cn.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-22 14:27
 **/
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExamplePlugin implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("走了插件的intercept方法");
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        /**
         * plugin：org.apache.ibatis.executor.CachingExecutor@96def03
         *
         * plugin：org.apache.ibatis.scripting.defaults.DefaultParameterHandler@5ae50ce6
         * plugin：org.apache.ibatis.executor.resultset.DefaultResultSetHandler@4a22f9e2
         * plugin：org.apache.ibatis.executor.statement.RoutingStatementHandler@3c419631
         */
        System.out.println("plugin：" + o.toString());
        return Plugin.wrap(o, this);
    }

    /**
     *  properties可以在mybatis的配置文件中配置插件的时候添加进去，具体见mybatis-config.xml文件
     * @param properties
     */
    public void setProperties(Properties properties) {
        System.out.println("setProperties：" + properties.toString());
    }
}
