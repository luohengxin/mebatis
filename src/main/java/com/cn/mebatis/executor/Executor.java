package com.cn.mebatis.executor;
import com.cn.mebatis.Configuration;
import com.cn.mebatis.PreparedStatementHandler;

import java.sql.*;
import java.util.List;

public interface Executor {


    public <T> List<T> query(String sql, Object[] param, Class clazz) ;
}
