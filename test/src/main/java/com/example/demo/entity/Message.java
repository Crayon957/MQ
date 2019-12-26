package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author menghm.
 * @description
 * @date 2019/9/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String content;
    private String time;
}
