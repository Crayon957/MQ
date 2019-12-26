package com.wpg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@Api(tags = "发布")
public class PublishController {

    Logger logger = getLogger(PublishController.class);

    @Autowired
    MqttMessage message;

    @Autowired
    MqttClient client;

    MqttTopic topic;

    @PostMapping("/t1")
    @ApiOperation("发布主题")
    public void setPublisController(String topic,String context) throws MqttException {
        this.topic = client.getTopic(topic);
        message.setPayload(context.getBytes());
        this.topic.publish(message);
        logger.info("发布成功");
    }

}
