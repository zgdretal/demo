package com.zgd.menhu.demo.service.test;

import org.springframework.stereotype.Service;

@Service
public class Target implements  TargetInterface{

    public void test(String test) {
        System.out.println(test);
    }
}
