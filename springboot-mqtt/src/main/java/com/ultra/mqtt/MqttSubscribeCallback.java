package com.ultra.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mqtt订阅接口实现
 *
 * @author fan
 */
public class MqttSubscribeCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttSubscribeCallback.class);

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("连接异常", throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        logger.info("接收到消息 topic:{},qos:{},content:{}", topic, mqttMessage.getQos(), new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("接收到消息完成:{}", iMqttDeliveryToken.isComplete());
    }
}
