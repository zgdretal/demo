package com.zgd.menhu.demo.service.test;

public class ProxyTest {


    public void dynamicProxy() throws Exception {
        Target target = new Target();
        TargetTwo targetTwo = new TargetTwo();
        TargetInterface proxy1 = (TargetInterface) DynamicProxyTest.getProxy(target);
        TargetInterface proxy2 = (TargetInterface) DynamicProxyTest.getProxy(targetTwo);
        proxy1.test("12345");
        proxy2.test("12345");
    }

    public static void main(String[] args) {
        ProxyTest proxyTest = new ProxyTest();
        try {
            proxyTest.dynamicProxy();
        } catch (Exception e) {

        }

    }

}
