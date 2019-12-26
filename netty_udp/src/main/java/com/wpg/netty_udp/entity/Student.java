package com.wpg.netty_udp.entity;

import lombok.Data;
import lombok.Synchronized;

@Data
public class Student  {

    /**
     * 学号
     */
    private String number;

    /**
     * 年级
     */
    private String grade;

    /**
     * 班级
     */
    private String classAndGrade;

    /**
     * 科目
     */
    private String subject ;

    /**
     * 成绩
     */
    private String scores;


}
