package com.cn.interceptor;

import com.cn.mebatis.executor.Executor;
import com.cn.mebatis.plugin.Interceptor;
import com.cn.mebatis.plugin.Invocation;
import com.cn.mebatis.plugin.Plugin;

import java.util.Properties;

public class LogInteceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("表示已经进入了插件");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Class supportClass() {
        return Executor.class;
    }

    @Override
    public boolean support(Object target) {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if(interfaces.length == 0){
            return false;
        }
        for(Class clazz :interfaces ){
            if(clazz.equals(supportClass())){
                return  true;
            }
        }
        return false;
    }
}
