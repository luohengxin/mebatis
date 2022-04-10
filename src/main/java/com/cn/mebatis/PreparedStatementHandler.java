package com.cn.mebatis;

import java.sql.*;
import java.util.List;

public class PreparedStatementHandler {

    Connection connection ;

    public PreparedStatementHandler(Connection connection) {
        this.connection = connection;
    }

    public <T> List<T> handler(String sql, Object[] param, Class clazz ){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < param.length ;i++){
                Object p = param[i];
                if(p instanceof String){
                    ps.setString(i+1,p.toString());
                }else if(p instanceof Integer){
                    ps.setInt(i+1,(int)p);
                }else{
                    ps.setString(i+1,p.toString());
                }
            }
            ResultSet resultSet = ps.executeQuery();
            return new ResultSetHandler().handler(resultSet,clazz);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
