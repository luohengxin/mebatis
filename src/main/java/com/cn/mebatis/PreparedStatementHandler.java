package com.cn.mebatis;

import java.sql.*;
import java.util.List;

public class PreparedStatementHandler {

    Connection connection ;

    ParameterHandler parameterHandler;

    public PreparedStatementHandler(ParameterHandler parameterHandler) {
        this.parameterHandler = parameterHandler;
    }

    public PreparedStatement prepareStatement(Connection connection,String sql, Object[] param) {

        PreparedStatement  ps = null;
        try {
            ps = connection.prepareStatement(sql);
            parameterHandler.handler(ps,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }



    public <T> List<T> handler(PreparedStatement ps , Class clazz ){
        try {

            ResultSet resultSet = ps.executeQuery();
            return new ResultSetHandler().handler(resultSet,clazz);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
