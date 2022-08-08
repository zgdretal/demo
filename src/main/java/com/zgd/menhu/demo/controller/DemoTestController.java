package com.zgd.menhu.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoTestController {

    @RequestMapping("/test")
    public String test() {
        return "testzgd";
    }
}
