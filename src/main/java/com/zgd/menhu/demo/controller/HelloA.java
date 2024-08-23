package com.zgd.menhu.demo.controller;

/**
 * @author zhangguodong
 * @date 2024年08月19日 17:00
 */
public class HelloA extends HelloB{
    {System.out.println("aaa");}

    static {System.out.println("staticaaaa");}

    public HelloA() {
        System.out.println("HelloA");
    }
    public static void main(String[] args) {
        new HelloA();
    }
}
