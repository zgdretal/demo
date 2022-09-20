package com.zgd.menhu.demo.service;


import java.util.List;

public class User {

    private String userName;

    private Integer uid;

    private Integer account;

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    private List<TranserInfo> transerInfoList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<TranserInfo> getTranserInfoList() {
        return transerInfoList;
    }

    public void setTranserInfoList(List<TranserInfo> transerInfoList) {
        this.transerInfoList = transerInfoList;
    }
}
