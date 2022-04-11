package com.cn.mebatis;

import com.cn.mebatis.executor.Executor;

import java.util.List;

public class SqlSession {

    Configuration configuration;
    Executor executor ;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = configuration.getExecutor();
    }

    public <T> T selectOne(String statementId,Object[] param,Class clazz){
        String sql = configuration.getSql(statementId);

        List<T> query = executor.query(sql, param, clazz);
        if(query.size() == 0){
            return null;
        }else if(query.size() > 1){
            throw new RuntimeException("有多条数");
        }
        return query.get(0);
    }

    public <T> T getMapper(Class clazz){
        return configuration.getMapper(this,clazz);
    }

}
