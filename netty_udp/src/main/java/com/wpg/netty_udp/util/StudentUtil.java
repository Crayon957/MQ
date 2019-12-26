package com.wpg.netty_udp.util;

import com.wpg.netty_udp.entity.Student;

import java.util.HashMap;


public class StudentUtil {
    public static HashMap<String,Student> transform(String msg){
        //判断数据是否异常
        if(!msg.startsWith("#")||!msg.endsWith("#")){
            throw new ArithmeticException("数据异常") ;
        }
        HashMap<String, Student> stuMap = new HashMap<>();

        //去除头尾
        msg = msg.substring(1,msg.length()-1);

        //分学生
        String[] stus = msg.split("[|]");

        //处理每一个学生数据
        for (String s:stus) {
            String[] stu = s.split("[-]");
            if(stu.length==5){
                Student student = new Student();
                student.setNumber(stu[0]);
                student.setGrade(stu[1]);
                student.setClassAndGrade(stu[2]);
                student.setSubject(stu[3]);
                student.setGrade(stu[4]);
                stuMap.put(stu[0],student);
            }else {
                throw new ArithmeticException("数据异常") ;
            }
        }

        return stuMap;
    }
}
