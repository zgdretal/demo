package com.zgd.menhu.demo.service;


import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class User {

    //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();

    public static void main(String[] args) {
        //threadPoolExecutor.execute();


        System.out.println(0.3d * 1 == 0.3d);
        System.out.println(0.2d * 1 == 0.2d);


        Integer l1 = 100;
        Integer l2 = 100;

        Integer l3 = 200;
        Integer l4 = 200;
        System.out.println(l1 == l2);
        System.out.println(l3 == l4);


        Double  d1 = 0.3d;
        Double  d2 = 0.3d;
        Double  d3 = 0.2d;
        Double  d4 = 0.2d;

        System.out.println(d1 == d2);
        System.out.println(d3 == d4);
    }

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
