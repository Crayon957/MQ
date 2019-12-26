package com.wpg.config;

import ch.qos.logback.core.net.server.Client;
import com.wpg.beans.Mqtt;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {
    @Autowired
    private Mqtt mqtt;

    @Bean
    public MqttClient client() throws MqttException {
        MqttClient client = new MqttClient(mqtt.getHostUrl(),mqtt.getClientId(),new MemoryPersistence());
        return client;
    }
    @Bean
    public MqttConnectOptions options(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqtt.getUsername());
        options.setPassword(mqtt.getPassword().toCharArray());
        //设置超时时间
        options.setConnectionTimeout(60);
        //设置会话心跳时间
        options.setKeepAliveInterval(20);
        return options;
    }
//    @Bean
//    public MqttTopic topic(MqttClient client){
//        MqttTopic topic = client.getTopic(mqtt.getMsgTopic()[0]);
//        return  topic;
//    }
    @Bean
    public MqttMessage message(){
        MqttMessage message = new MqttMessage();
        message.setQos(0);
        message.setRetained(true);
        //message.setPayload("topictest".getBytes());
        return message;
    }

}
