package com.dji.sdk.mqtt.property;

import com.dji.sdk.common.Common;
import com.dji.sdk.mqtt.Chan;
import com.dji.sdk.mqtt.ChannelName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * MQTT 프로퍼티 설정 응답 핸들러 클래스
 * 프로퍼티 설정 응답 메시지를 처리하는 핸들러
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Component
public class PropertySetReplyHandler {

    /** 응답 결과 키 */
    private static final String RESULT_KEY = "result";

    /**
     * "/property/set_reply" 토픽으로부터의 응답 메시지를 처리합니다.
     * 
     * @param message 응답 메시지
     * @throws IOException JSON 파싱 오류 시 발생
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_PROPERTY_SET_REPLY)
    public void propertySetReply(Message<?> message) throws IOException {
        byte[] payload = (byte[])message.getPayload();

        // 메시지를 TopicPropertySetResponse 객체로 변환
        TopicPropertySetResponse receiver = Common.getObjectMapper().readValue(payload, new TypeReference<TopicPropertySetResponse>() {});
        // 트랜잭션 ID로 해당하는 Chan 객체를 찾음
        Chan chan = Chan.getInstance(receiver.getTid(), false);
        if (Objects.isNull(chan)) {
            return;
        }
        // 응답 데이터에서 결과 코드를 추출하여 설정
        receiver.setData(PropertySetReplyResultEnum.find(
                Common.getObjectMapper().convertValue(receiver.getData(), JsonNode.class).findValue(RESULT_KEY).intValue()));
        // 응답을 Chan 객체에 전달
        chan.put(receiver);
    }
}
