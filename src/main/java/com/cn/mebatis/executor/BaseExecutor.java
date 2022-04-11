package com.cn.mebatis.executor;

import com.cn.mebatis.Configuration;
import com.cn.mebatis.PreparedStatementHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class BaseExecutor implements Executor{

    private Configuration configuration;

    public BaseExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> query(String sql, Object[] param, Class clazz) {
        Connection connection = configuration.getConnection();
        PreparedStatementHandler preparedStatementHandler  = configuration.getPreparedStatementHandler();
        PreparedStatement prepareStatement = preparedStatementHandler.prepareStatement(connection,sql,param);
        return preparedStatementHandler.handler(prepareStatement, clazz);
    }
}
