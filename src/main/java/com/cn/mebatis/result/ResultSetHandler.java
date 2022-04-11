package com.cn.mebatis.result;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public interface ResultSetHandler {

     <T>  List<T>  handler(ResultSet resultSet, Class<T> clazz);


}
