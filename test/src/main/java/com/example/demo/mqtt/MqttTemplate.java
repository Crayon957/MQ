package com.example.demo.mqtt;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.Charset;


/**
 * @author jzh
 */
@Slf4j
public class MqttTemplate extends MqttClient {

    private static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");

    public MqttTemplate(String serverURI) throws MqttException {
        super(serverURI, "" + MqttAsyncClient.generateClientId(), new MemoryPersistence());
    }

    public static MqttConnectOptions defaultOptions(String userName, String password) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(password.toCharArray());
        return mqttConnectOptions;
    }

    /**
     * 发送消息到指定的Topic
     *
     * @param topic
     * @param data
     */
    public <T> void publish(String topic, T data) {
        try {
            if (data instanceof String) {
                publish(topic, new MqttMessage(((String) data).getBytes()));
            } else {
                publish(topic, new MqttMessage(JSONObject.toJSONBytes(data)));
            }

        } catch (Exception e) {
            log.error("Mqtt发送消息异常", e);
            try {
                Thread.sleep(5000);
                if (!super.isConnected()) {
                    super.reconnect();
                }
            } catch (MqttException | InterruptedException ignore) {
            }
        }
    }


}

