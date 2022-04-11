package com.cn.mebatis.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseParameterHandler implements ParameterHandler{

    @Override
    public void handler(PreparedStatement ps ,Object[] param){
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
