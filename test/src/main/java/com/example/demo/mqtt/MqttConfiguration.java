package com.example.demo.mqtt;

import lombok.Data;

/**
 * @author menghm.
 * @description
 * @date 2019/9/23.
 */
@Data
public class MqttConfiguration {
    /**
     * Mqtt服务器的地址
     */
    private String url;

    /**
     * MQTT服务端口
     */
    private Integer port;

    /**
     * 默认需要订阅的topic
     */
    private String topic;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;
}
