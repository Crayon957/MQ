package com.wpg.util;

import com.wpg.beans.Mqtt;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;

public class PushCallback implements MqttCallback {

    @Autowired
    private MqttClient client;

    @Autowired
    private MqttConnectOptions options;

    @Autowired
    private Mqtt mqtt;
    /**
     * 断开重连
     * */
    @Override
    public void connectionLost(Throwable throwable) {
        while(true) {
            try {
                Thread.sleep(30000);
                client.connect(options);
                //订阅消息
                client.subscribe(mqtt.getMsgTopic()[0],1);
                System.out.println("重连");
                break;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 消息处理
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        //订阅消息字符
        String msg=new String(mqttMessage.getPayload());
        System.out.println("--------订阅消息---------------");
        System.out.println("topic:"+s);
        System.out.println("msg:"+msg);
        System.out.println("-------------------------------");
    }

    /**
     * 接收到消息调用令牌中调用
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
