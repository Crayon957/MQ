package com.wpg.netty_udp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentJson implements Serializable {

    private String number;

    private Student values;
}
