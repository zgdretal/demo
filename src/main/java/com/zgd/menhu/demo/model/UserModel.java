package com.zgd.menhu.demo.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private Integer groupId;
}
