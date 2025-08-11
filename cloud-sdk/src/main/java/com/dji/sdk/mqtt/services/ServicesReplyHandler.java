package com.dji.sdk.mqtt.services;

import com.dji.sdk.cloudapi.log.FileUploadListResponse;
import com.dji.sdk.cloudapi.log.LogMethodEnum;
import com.dji.sdk.common.Common;
import com.dji.sdk.mqtt.Chan;
import com.dji.sdk.mqtt.ChannelName;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * MQTT 서비스 응답 핸들러 클래스
 * 서비스 응답 메시지를 처리하는 핸들러
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Component
public class ServicesReplyHandler {

    /**
     * "/services_reply" 토픽으로부터의 응답 메시지를 처리합니다.
     * 
     * @param message 응답 메시지
     * @throws IOException JSON 파싱 오류 시 발생
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_SERVICES_REPLY)
    public void servicesReply(Message<?> message) throws IOException {
        byte[] payload = (byte[])message.getPayload();

        // 메시지를 TopicServicesResponse 객체로 변환
        TopicServicesResponse<ServicesReplyReceiver> receiver = Common.getObjectMapper()
                .readValue(payload, new TypeReference<TopicServicesResponse<ServicesReplyReceiver>>() {});
        // 트랜잭션 ID로 해당하는 Chan 객체를 찾음
        Chan chan = Chan.getInstance(receiver.getTid(), false);
        if (Objects.isNull(chan)) {
            return;
        }
        // 파일 업로드 리스트 메서드인 경우 특별 처리
        if (LogMethodEnum.FILE_UPLOAD_LIST.getMethod().equals(receiver.getMethod())) {
            receiver.getData().setOutput(Common.getObjectMapper().convertValue(receiver.getData(),
                    new TypeReference<FileUploadListResponse>() {}));
        }
        // 응답을 Chan 객체에 전달
        chan.put(receiver);
    }
}
