package com.zgd.menhu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.Cookie;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        /*ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.get();*/
        SpringApplication.run(DemoApplication.class, args);
    }

}
