package com.example.demo.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author menghm.
 * @description
 * @date 2019/9/23.
 */
@Configuration
@Slf4j
public class MqttInit {

    private static final String TEMPLATE_MQTT_URL = "tcp://%s:%d";

    @Bean
    @ConfigurationProperties(prefix = "custom.mqtt")
    MqttConfiguration baseMqttConfiguration() {
        return new MqttConfiguration();
    }

    @Bean
    public MqttTemplate mqttTemplate(MqttConfiguration baseMqttConfiguration) throws MqttException {
        String serviceURI = String.format(TEMPLATE_MQTT_URL, baseMqttConfiguration.getUrl(), baseMqttConfiguration.getPort());
        MqttTemplate mqttTemplate = new MqttTemplate(serviceURI);
        mqttTemplate.connect(MqttTemplate.defaultOptions(baseMqttConfiguration.getUserName(), baseMqttConfiguration.getPassWord()));
        log.info("Mqtt connected with mqtt broker at url : " + serviceURI);
        return mqttTemplate;
    }
}
