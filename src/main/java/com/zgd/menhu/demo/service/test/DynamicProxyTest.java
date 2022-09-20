package com.zgd.menhu.demo.service.test;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static Object getProxy(Object target) throws Exception {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 指定目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个，这是一个数组
                new TargetInvocationHandler(target)   // 代理对象处理器
        );
        return proxy;
    }


}
