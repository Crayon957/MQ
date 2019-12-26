package com.wpg.netty_udp.mqtt;

import lombok.Data;

@Data
public class MqttConfig  {
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
