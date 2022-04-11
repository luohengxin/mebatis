package com.cn.mebatis.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Plugin implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(interceptor.support(proxy)){
            interceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,args);
    }


    public static Object wrap(Object target, Interceptor interceptor) {
        Class<?> type = interceptor.supportClass();
            return Proxy.newProxyInstance(
                    type.getClassLoader(),
                    new Class[]{type},
                    new Plugin(target, interceptor));
    }
}
