package com.dji.sdk.mqtt;

import com.dji.sdk.common.BaseModel;
import com.dji.sdk.common.Common;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import com.dji.sdk.mqtt.state.TopicStateRequest;
import com.dji.sdk.mqtt.state.TopicStateResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * MQTT 응답 핸들러
 * 
 * 이 클래스는 MQTT 응답을 처리하기 위한 AOP(Aspect-Oriented Programming) 핸들러입니다.
 * Spring AOP를 사용하여 MQTT API 메서드의 반환값을 자동으로 검증하고 수정합니다.
 * 
 * 주요 기능:
 * - 응답값 검증 및 수정
 * - 이벤트 응답 처리
 * - 요청 응답 처리
 * - 상태 응답 처리
 * 
 * 이 클래스는 MQTT 통신의 응답 형식을
 * 표준화된 방식으로 처리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/22
 */
@Aspect
@Component
public class MqttReplyHandler {

    /**
     * 반환값을 검증하고 수정합니다.
     * 
     * MQTT API 메서드의 반환값을 가로채서 검증하고 필요한 정보를 추가합니다.
     * 요청과 응답의 tid, bid를 일치시키고 타임스탬프를 설정합니다.
     * 
     * @param point 조인 포인트 (메서드 실행 정보)
     * @param result 반환값
     * @return 수정된 반환값
     */
    @AfterReturning(value = "execution(public com.dji.sdk.mqtt.CommonTopicResponse+ com.dji.sdk.cloudapi.*.api.*.*(com.dji.sdk.mqtt.CommonTopicRequest+, org.springframework.messaging.MessageHeaders))", returning = "result")
    public Object validateReturnValue(JoinPoint point, CommonTopicResponse result) {
        if (Objects.isNull(result)) {
            return null;
        }
        CommonTopicRequest request = (CommonTopicRequest) point.getArgs()[0];
        result.setBid(request.getBid()).setTid(request.getTid()).setTimestamp(System.currentTimeMillis());
        if (result instanceof TopicEventsResponse) {
            fillEvents((TopicEventsResponse) result, (TopicEventsRequest) request);
        } else if (result instanceof TopicRequestsResponse) {
            validateRequests((TopicRequestsResponse) result, (TopicRequestsRequest) request);
        } else if (result instanceof TopicStateResponse) {
            fillState((TopicStateResponse) result, (TopicStateRequest) request);
        }
        return result;
    }

    /**
     * 이벤트 응답을 채웁니다.
     * 
     * 이벤트 응답에 필요한 정보를 추가합니다.
     * 응답이 필요하지 않은 경우 데이터를 null로 설정하고,
     * 필요한 경우 성공 응답을 설정합니다.
     * 
     * @param response 이벤트 응답
     * @param request 이벤트 요청
     */
    private void fillEvents(TopicEventsResponse response, TopicEventsRequest request) {
        if (!request.isNeedReply()) {
            response.setData(null);
            return;
        }
        response.setMethod(request.getMethod()).setData(MqttReply.success());
    }

    /**
     * 요청 응답을 검증합니다.
     * 
     * 요청 응답의 데이터를 검증하고 필요한 경우 모델을 검증합니다.
     * MqttReply 형태의 데이터인 경우 결과 코드를 확인하고
     * 성공인 경우에만 출력 데이터를 검증합니다.
     * 
     * @param response 요청 응답
     * @param request 요청 요청
     */
    private void validateRequests(TopicRequestsResponse response, TopicRequestsRequest request) {
        response.setMethod(request.getMethod());
        Object data = response.getData();
        if (data instanceof MqttReply) {
            MqttReply mqttData = (MqttReply) data;
            if (MqttReply.CODE_SUCCESS != mqttData.getResult()) {
                return;
            }
            data = mqttData.getOutput();
        }
        Common.validateModel((BaseModel) data);
    }

    /**
     * 상태 응답을 채웁니다.
     * 
     * 상태 응답에 필요한 정보를 추가합니다.
     * 응답이 필요한 경우 성공 응답을 설정하고,
     * 그렇지 않은 경우 데이터를 null로 설정합니다.
     * 
     * @param response 상태 응답
     * @param request 상태 요청
     */
    private void fillState(TopicStateResponse response, TopicStateRequest request) {
        response.setData(request.isNeedReply() ? MqttReply.success() : null);
    }
}
