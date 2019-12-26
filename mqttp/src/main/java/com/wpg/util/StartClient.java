package com.wpg.util;

import com.wpg.beans.Mqtt;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class StartClient implements ApplicationRunner {

    Logger logger = getLogger(StartClient.class);

    @Autowired
    Mqtt mqtt;

    @Autowired
    MqttClient client;

    @Autowired
    MqttConnectOptions options;


    private void init(){
        logger.info("启动服务器");
        contextLoads();
        logger.info("连接服务器成功");
    }
    /**
     * 订阅
     * */
    private void contextLoads()  {
        //设置回调函数
        client.setCallback(new PushCallback());
        try {
            //建立连接
            client.connect(options);
            //订阅主题
            client.subscribe(mqtt.getMsgTopic()[0],0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
}
