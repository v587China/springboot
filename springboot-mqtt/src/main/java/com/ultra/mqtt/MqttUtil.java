package com.ultra.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mqtt工具类
 *
 * @author fan
 */
public class MqttUtil {
    private static final Logger logger = LoggerFactory.getLogger(MqttUtil.class);
    /**
     * mqtt 客户端缓存
     */
    private static final Map<String, MqttClient> MQTT_CLIENT_CACHE = new ConcurrentHashMap<>();

    /**
     * 客户端连接
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀
     * @param username    用户名
     * @param password    密码
     * @param timeout     超时时间
     * @param keepalive   保留数
     * @return mqtt客户端
     */
    private static MqttClient getMqttClient(String uri, String clientIdPre, String username, String password, int timeout, int keepalive, MqttCallback callback) {
        MqttClient client = null;
        try {
            client = MQTT_CLIENT_CACHE.get(uri);
            if (client == null) {
                synchronized (MqttUtil.class) {
                    client = MQTT_CLIENT_CACHE.get(uri);
                    if (client == null) {
                        client = new MqttClient(uri, clientIdPre + System.currentTimeMillis(), new MemoryPersistence());
                        MqttConnectOptions options = new MqttConnectOptions();
                        options.setUserName(username);
                        options.setPassword(password.toCharArray());
                        options.setConnectionTimeout(timeout);
                        options.setKeepAliveInterval(keepalive);
                        options.setCleanSession(true);
                        options.setAutomaticReconnect(true);
                        if (callback != null) {
                            client.setCallback(callback);
                        }
                        client.connect(options);
                        MQTT_CLIENT_CACHE.put(uri, client);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("connect to {} is error", uri, e);
        }
        return client;
    }

    /**
     * 发布
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀
     * @param username    用户名
     * @param password    密码
     * @param topic       主题
     * @param content     消息体
     */
    public static void publish(String uri, String clientIdPre, String username, String password, String topic, String content) {
        publish(uri, clientIdPre, username, password, topic, content, 1, false, 30, 60);
    }

    /**
     * 发布
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀：clientIdPre + 时间戳 = clientId
     * @param username    用户名
     * @param password    密码
     * @param topic       主题
     * @param content     消息体
     * @param qos         连接方式
     * @param retained    是否保留
     * @param timeout     超时时间
     * @param keepalive   保留数
     */
    public static void publish(String uri, String clientIdPre, String username, String password, String topic, String content, int qos, boolean retained, int timeout, int keepalive) {
        if (logger.isDebugEnabled()) {
            logger.debug("uri:{},clientIdPre:{},username:{},password:{},topic:{},content:{}", uri, clientIdPre, username, password, topic, content);
        }
        try {
            MqttClient client = getMqttClient(uri, clientIdPre, username, password, timeout, keepalive, null);
            if (client != null && client.isConnected()) {
                MqttMessage message = new MqttMessage(content.getBytes(StandardCharsets.UTF_8));
                message.setQos(qos);
                message.setRetained(retained);
                client.publish(topic, message);
            }
        } catch (MqttException e) {
            logger.error("", e);
        }
    }

    /**
     * 订阅
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀：clientIdPre + 时间戳 = clientId
     * @param username    用户名
     * @param password    密码
     * @param topic       主题
     * @param callback    回调函数
     */
    public static void subscribe(String uri, String clientIdPre, String username, String password, String topic, MqttCallback callback) {
        subscribe(uri, clientIdPre, username, password, topic, 0, 30, 60, callback);
    }

    /**
     * 订阅
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀：clientIdPre + 时间戳 = clientId
     * @param username    用户名
     * @param password    密码
     * @param topic       主题
     * @param qos         连接方式
     * @param timeout     超时时间
     * @param keepalive   保留数
     * @param callback    回调函数
     */
    public static void subscribe(String uri, String clientIdPre, String username, String password, String topic, int qos, int timeout, int keepalive, MqttCallback callback) {
        if (logger.isDebugEnabled()) {
            logger.debug("uri:{},clientIdPre:{},username:{},password:{},topic:{}", uri, clientIdPre, username, password, topic);
        }
        try {
            MqttClient client = getMqttClient(uri, clientIdPre, username, password, timeout, keepalive, callback);
            if (client != null && client.isConnected()) {
                client.subscribe(topic, qos);
                logger.info("订阅主题成功：{}", topic);
            }
        } catch (MqttException e) {
            logger.error("订阅主题失败", e);
        }
    }
}
