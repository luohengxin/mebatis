package com.cn.mebatis;

import com.cn.mebatis.executor.BaseExecutor;
import com.cn.mebatis.executor.Executor;
import com.cn.mebatis.parameter.BaseParameterHandler;
import com.cn.mebatis.parameter.ParameterHandler;
import com.cn.mebatis.plugin.Interceptor;
import com.cn.mebatis.plugin.InterceptorChain;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Configuration {

    private final static ResourceBundle SQL_PROPERTIES = ResourceBundle.getBundle("sql");
    private final static ResourceBundle DATABASE_PROPERTIES = ResourceBundle.getBundle("database");

    InterceptorChain interceptorChain = new InterceptorChain();


    public Configuration() {
        // 可以在构造方法初始话需要的内容
    }

    public void addInteceptor(Interceptor interceptor){
        interceptorChain.addInterceptor(interceptor);
    }

    public <T> T getMapper(SqlSession sqlSession, Class clazz) {

        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

    public String getSql(String statementId) {
        return SQL_PROPERTIES.getString(statementId);
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_PROPERTIES.getString("url"), DATABASE_PROPERTIES.getString("user"), DATABASE_PROPERTIES.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public Executor getExecutor() {
        return (Executor) interceptorChain.pluginAll(new BaseExecutor(this));
    }

    public PreparedStatementHandler getPreparedStatementHandler() {
        return new PreparedStatementHandler(getParameterHandler());
    }
    public ParameterHandler getParameterHandler() {
        return new BaseParameterHandler();
    }
}
