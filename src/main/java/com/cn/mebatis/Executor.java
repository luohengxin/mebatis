package com.cn.mebatis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;
import java.util.Locale;

public class Executor {

    private Configuration configuration;

    public Executor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> List<T> query(String sql, Object[] param, Class clazz) {
        Connection connection = configuration.getConnection();
        PreparedStatementHandler preparedStatementHandler = new PreparedStatementHandler(connection);
        return preparedStatementHandler.handler(sql, param, clazz);
    }
}
