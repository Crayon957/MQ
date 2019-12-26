package com.wpg.netty_udp.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.ConstructorProperties;


@Configuration
@Slf4j
public class MqttInit {

    private static final String TEMPLATE_MQTT_URL = "tcp://%s:%d";

    @Bean
    @ConfigurationProperties(prefix = "custom.mqtt")
    MqttConfig baseMqttConfig(){ return  new MqttConfig(); }

    @Bean
    public  MqttTemplate mqttTemplate(MqttConfig baseMqttConfig) throws MqttException{
        String serviceURI = String.format(TEMPLATE_MQTT_URL, baseMqttConfig.getUrl(), baseMqttConfig.getPort());
        MqttTemplate mqttTemplate = new MqttTemplate(serviceURI);
        mqttTemplate.connect(MqttTemplate.defaultOptions(baseMqttConfig.getUserName(), baseMqttConfig.getPassWord()));
        log.info("Mqtt connected with mqtt broker at url : " + serviceURI);
        return mqttTemplate;
    }

}
