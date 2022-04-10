package com.cn.mebatis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultSetHandler {

    public <T>  List<T>  handler(ResultSet resultSet, Class<T> clazz){
        List<T> res = new ArrayList<>();
        try {
            while (resultSet.next()){
                Object result = clazz.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for(int i = 0 ; i < columnCount ;i++){
                    try {
                        String columnName = metaData.getColumnName(i + 1);
                        Field field = clazz.getDeclaredField(columnName);
                        Class<?> type = field.getType();
                        Method method = clazz.getMethod("set" + firstUpper(columnName), type);
                        method.invoke(result,resultSet.getObject(i+1));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                res.add((T)result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }


    private String firstUpper(String columnName){
        return columnName.substring(0,1).toUpperCase(Locale.ROOT).concat(columnName.substring(1));
    }
}
