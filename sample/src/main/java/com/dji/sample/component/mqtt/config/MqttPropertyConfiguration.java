package com.dji.sample.component.mqtt.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.dji.sample.common.util.JwtUtil;
import com.dji.sample.component.mqtt.model.MqttClientOptions;
import com.dji.sample.component.mqtt.model.MqttProtocolEnum;
import com.dji.sample.component.mqtt.model.MqttUseEnum;
import com.dji.sdk.cloudapi.control.DrcModeMqttBroker;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * MQTT 프로퍼티 설정 클래스
 * MQTT 클라이언트 연결 설정과 관련된 프로퍼티를 관리
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Configuration
@Data
@ConfigurationProperties
public class MqttPropertyConfiguration {

    /** MQTT 클라이언트 옵션 맵 */
    private static Map<MqttUseEnum, MqttClientOptions> mqtt;

    /**
     * MQTT 클라이언트 옵션 맵을 설정합니다.
     * @param mqtt MQTT 클라이언트 옵션 맵
     */
    public void setMqtt(Map<MqttUseEnum, MqttClientOptions> mqtt) {
        MqttPropertyConfiguration.mqtt = mqtt;
    }

    /**
     * 기본 링크의 MQTT 클라이언트 설정 옵션을 가져옵니다.
     * @return 기본 MQTT 클라이언트 옵션
     */
    static MqttClientOptions getBasicClientOptions() {
        if (!mqtt.containsKey(MqttUseEnum.BASIC)) {
            throw new Error("Please configure the basic mqtt connection parameters first, otherwise application cannot be started.");
        }
        return mqtt.get(MqttUseEnum.BASIC);
    }

    /**
     * 기본 링크의 MQTT 주소를 가져옵니다.
     * @return 기본 MQTT 주소
     */
    public static String getBasicMqttAddress() {
        return getMqttAddress(getBasicClientOptions());
    }

    /**
     * 다양한 클라이언트의 매개변수에 따라 MQTT 주소를 조합합니다.
     * @param options MQTT 클라이언트 옵션
     * @return 조합된 MQTT 주소
     */
    private static String getMqttAddress(MqttClientOptions options) {
        StringBuilder addr = new StringBuilder()
                .append(options.getProtocol().getProtocolAddr())
                .append(options.getHost().trim())
                .append(":")
                .append(options.getPort());
        // WebSocket 프로토콜인 경우 경로 추가
        if ((options.getProtocol() == MqttProtocolEnum.WS || options.getProtocol() == MqttProtocolEnum.WSS)
                && StringUtils.hasText(options.getPath())) {
            addr.append(options.getPath());
        }
        return addr.toString();
    }

    /**
     * DRC 링크의 MQTT 클라이언트 연결 매개변수를 가져옵니다.
     * @param clientId 클라이언트 ID
     * @param username 사용자명
     * @param age 토큰의 유효 기간 (초 단위)
     * @param map 토큰에 추가할 사용자 정의 데이터
     * @return DRC 모드 MQTT 브로커 설정
     */
    public static DrcModeMqttBroker getMqttBrokerWithDrc(String clientId, String username, Long age, Map<String, ?> map) {
        if (!mqtt.containsKey(MqttUseEnum.DRC)) {
            throw new RuntimeException("Please configure the drc link parameters of mqtt in the backend configuration file first.");
        }
        Algorithm algorithm = JwtUtil.algorithm;

        // JWT 토큰 생성
        String token = JwtUtil.createToken(map, age, algorithm, null, null);

        return new DrcModeMqttBroker()
                .setAddress(getMqttAddress(mqtt.get(MqttUseEnum.DRC)))
                .setUsername(username)
                .setClientId(clientId)
                .setExpireTime(System.currentTimeMillis() / 1000 + age)
                .setPassword(token)
                .setEnableTls(false);
    }

    /**
     * MQTT 연결 옵션을 생성합니다.
     * @return MQTT 연결 옵션
     */
    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttClientOptions customizeOptions = getBasicClientOptions();
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(new String[]{ getBasicMqttAddress() });
        mqttConnectOptions.setUserName(customizeOptions.getUsername());
        mqttConnectOptions.setPassword(StringUtils.hasText(customizeOptions.getPassword()) ?
                customizeOptions.getPassword().toCharArray() : new char[0]);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setKeepAliveInterval(10);
        return mqttConnectOptions;
    }

    /**
     * MQTT 클라이언트 팩토리를 생성합니다.
     * @return MQTT 클라이언트 팩토리
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }
}
