package com.wpg.netty_tcp.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AttendJson implements Serializable {
    /**
     * 员工编号
     * */
    private String number;

    /**
     * 员工
     */
    private List<Attend> values;
}
