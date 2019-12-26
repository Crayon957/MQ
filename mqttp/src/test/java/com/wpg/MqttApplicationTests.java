package com.wpg;

import com.wpg.beans.Mqtt;
import com.wpg.util.PushCallback;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class MqttApplicationTests {

//    @Autowired
//    Mqtt mqtt;
//
//    @Autowired
//    MqttClient client;
//
//    @Autowired
//    MqttMessage message;
//
//    @Autowired
//    MqttTopic topic;
//
//    @Autowired
//    MqttConnectOptions options;
//
//    /**
//     * 订阅
//     * */
//    @Test
//    void contextLoads() throws MqttException {
//        //设置回调函数
//        client.setCallback(new PushCallback());
//        //建立连接
//        client.connect(options);
//        //订阅主题
//        client.subscribe(mqtt.getMsgTopic()[0],0);
//    }
//
//    /**
//     * 发布
//     * */
//    @Test
//    void contextStart() throws MqttException {
//        //设置回调函数
//        client.setCallback(new PushCallback());
//        //连接服务器
//        client.connect(options);
//        //发布主题
//        topic.publish(message);
//    }

}
