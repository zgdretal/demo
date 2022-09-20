package com.zgd.menhu.demo.service.test;

import java.util.concurrent.ThreadPoolExecutor;

public class TargetTwo implements TargetInterface{
    @Override
    public void test(String test) {
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1);
        System.out.println(test + "two");
    }
}
