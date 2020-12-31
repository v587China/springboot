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
            logger.debug("uri:{},clientIdPre:{},username:{},password:{},topic:{},content:{},qos:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, topic, content, qos, timeout, keepalive);
        }
        try {
            MqttClient client = getMqttClient(uri, clientIdPre, username, password, timeout, keepalive);
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
     * 设置订阅的回调函数
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀：clientIdPre + 时间戳 = clientId
     * @param username    用户名
     * @param password    密码
     * @param callback    回调函数
     */
    public static void callback(String uri, String clientIdPre, String username, String password, MqttCallback callback) {
        callback(uri, clientIdPre, username, password, 30, 60, callback);
    }

    /**
     * 设置订阅的回调函数
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀
     * @param username    用户名
     * @param password    密码
     * @param timeout     超时时间
     * @param keepalive   保留数
     * @param callback    回调函数
     * @return 结果
     */
    public static boolean callback(String uri, String clientIdPre, String username, String password, int timeout, int keepalive, MqttCallback callback) {
        if (logger.isDebugEnabled()) {
            logger.debug("uri:{},clientIdPre:{},username:{},password:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, timeout, keepalive);
        }
        MqttClient client = getMqttClient(uri, clientIdPre, username, password, timeout, keepalive);
        if (client != null && client.isConnected()) {
            client.setCallback(callback);
            logger.info("设置回调函数成功：uri:{},clientIdPre:{},username:{},password:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, timeout, keepalive);
            return true;
        }
        logger.info("设置回调函数失败：uri:{},clientIdPre:{},username:{},password:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, timeout, keepalive);
        return false;
    }

    /**
     * 订阅
     *
     * @param uri         协议://ip+端口
     * @param clientIdPre 客户端Id前缀：clientIdPre + 时间戳 = clientId
     * @param username    用户名
     * @param password    密码
     * @param topic       主题
     * @return 结果
     */
    public static boolean subscribe(String uri, String clientIdPre, String username, String password, String topic) {
        return subscribe(uri, clientIdPre, username, password, topic, 0, 30, 60);
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
     * @return 结果
     */
    public static boolean subscribe(String uri, String clientIdPre, String username, String password, String topic, int qos, int timeout, int keepalive) {
        if (logger.isDebugEnabled()) {
            logger.debug("uri:{},clientIdPre:{},username:{},password:{},topic:{},qos:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, topic, qos, timeout, keepalive);
        }
        try {
            MqttClient client = getMqttClient(uri, clientIdPre, username, password, timeout, keepalive);
            if (client != null && client.isConnected()) {
                client.subscribe(topic, qos);
                logger.info("订阅主题成功：uri:{},clientIdPre:{},username:{},password:{},topic:{},qos:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, topic, qos, timeout, keepalive);
                return true;
            }
        } catch (MqttException e) {
            logger.error("订阅主题失败：uri:{},clientIdPre:{},username:{},password:{},topic:{},qos:{},timeout:{},keepalive:{}", uri, clientIdPre, username, password, topic, qos, timeout, keepalive, e);
        }
        return false;
    }

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
    private static MqttClient getMqttClient(String uri, String clientIdPre, String username, String password, int timeout, int keepalive) {
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

}
