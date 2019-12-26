package com.wpg.netty_tcp.entity.eunms;

public enum  Status1Eunm {
    上午正常("1"),上午迟到("0"),上午早退("2"),上午请假("100");

    private String s;
    Status1Eunm(String s){
        this.s=s;
    }
}
