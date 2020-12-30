package com.ultra;

import com.ultra.mqtt.MqttSubscribeCallback;
import com.ultra.mqtt.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.junit.Test;

/**
 * mqtt测试类
 *
 * @author fan
 */
public class MqttUtilTest {
    private static final String URI = "tcp://172.16.253.186:1883";
    private static final String CLIENT_ID_PRE = "smartwall";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String TOPIC = "/sw/mp/event";
    private static final String content = "{\"alarmState\":2,\"alarmType\":1,\"daId\":1608807149174816,\"daName\":\"防区1\",\"id\":-178161989349206133,\"time\":1608867994000,\"xyz\":[{\"x\":485.1711428571429,\"y\":445.13692857142854,\"z\":0}]}";
    private static final MqttCallback mqttCallback = new MqttSubscribeCallback();

    @Test
    public void publish() {
        MqttUtil.subscribe(URI, CLIENT_ID_PRE, USERNAME, PASSWORD, TOPIC, mqttCallback);
        MqttUtil.publish(URI, CLIENT_ID_PRE, USERNAME, PASSWORD, TOPIC, content);
//        while (true) {
//            MqttUtil.publish(URI, CLIENT_ID_PRE + System.currentTimeMillis(), USERNAME, PASSWORD, TOPIC, content);
//        }
    }

}
