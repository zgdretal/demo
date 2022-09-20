package com.zgd.menhu.demo.service;

public enum TransferType {

    PAY("支付", 1),
    SHOUKUAN("收款", 2);

    TransferType(String name, Integer typeId) {
        this.name = name;
        this.typeId = typeId;
    }

    private Integer typeId;
    private String name;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
