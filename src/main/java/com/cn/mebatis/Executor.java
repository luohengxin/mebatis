package com.cn.mebatis;
import java.sql.*;
import java.util.List;

public class Executor {

    private Configuration configuration;

    public Executor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> List<T> query(String sql, Object[] param, Class clazz) {
        Connection connection = configuration.getConnection();
        PreparedStatementHandler preparedStatementHandler  = configuration.getPreparedStatementHandler();
        PreparedStatement prepareStatement = preparedStatementHandler.prepareStatement(connection,sql,param);
        return preparedStatementHandler.handler(prepareStatement, clazz);
    }
}
