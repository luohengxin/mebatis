package com.cn.mebatis;

import com.cn.mebatis.parameter.ParameterHandler;
import com.cn.mebatis.result.BaseResultSetHandler;
import com.cn.mebatis.result.ResultSetHandler;

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
            return new BaseResultSetHandler().handler(resultSet,clazz);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
