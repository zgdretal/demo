package com.zgd.menhu.demo;

import com.zgd.menhu.demo.service.test.DynamicProxyTest;
import com.zgd.menhu.demo.service.test.Target;
import com.zgd.menhu.demo.service.test.TargetInterface;

public class TestZgd {


    public void dynamicProxy() throws Exception {
        Target target = new Target();
        TargetInterface proxy = (TargetInterface) DynamicProxyTest.getProxy(target);
        proxy.test("12345");
    }
}
