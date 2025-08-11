package com.dji.sample.control.service;

import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.control.model.enums.RemoteDebugMethodEnum;
import com.dji.sample.control.model.param.*;
import com.dji.sdk.common.HttpResultResponse;

/**
 * 제어 서비스 인터페이스
 * 
 * 드론과 도크의 제어 기능을 제공하는 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 정의합니다:
 * 
 * 1. 원격 디버깅 (Remote Debugging)
 *    - 도크의 원격 디버깅 명령 실행
 *    - 복귀 및 복귀 취소 기능
 *    - 디버깅 상태 모니터링
 * 
 * 2. 비행 제어 (Flight Control)
 *    - 특정 지점으로 비행 (Fly to Point)
 *    - 특정 지점으로 이륙 (Takeoff to Point)
 *    - 비행 중지 (Stop Flight)
 *    - 비행 진행 상황 모니터링
 * 
 * 3. 페이로드 제어 (Payload Control)
 *    - 카메라, 짐벌 등 페이로드 장치 제어
 *    - 페이로드 명령 실행
 *    - 페이로드 권한 관리
 * 
 * 4. 권한 관리 (Authority Management)
 *    - 비행 권한 획득
 *    - 페이로드 권한 획득
 *    - 권한 상태 확인
 * 
 * 각 메서드는 안전한 제어를 위해 실행 전 조건 검증을 수행하며,
 * HTTP 응답 형태로 결과를 반환합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
public interface IControlService {

    /**
     * 도크를 원격으로 디버깅합니다.
     * 
     * 이 메서드는 도크에 원격 디버깅 명령을 전송하여 다음과 같은 기능을 수행합니다:
     * - 복귀 명령 실행 (RETURN_HOME)
     * - 복귀 취소 명령 실행 (RETURN_HOME_CANCEL)
     * - 기타 디버깅 명령 실행
     * 
     * 실행 전 도크의 온라인 상태와 디버깅 조건을 검증합니다.
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @param serviceIdentifier 원격 디버깅 메서드 식별자 (복귀, 복귀취소 등)
     * @param param 원격 디버깅 파라미터 (필요에 따라 null 가능)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     */
    HttpResultResponse controlDockDebug(String sn, RemoteDebugMethodEnum serviceIdentifier, RemoteDebugParam param);

    /**
     * 드론을 목표 지점으로 비행시킵니다.
     * 
     * 이 메서드는 드론을 지정된 좌표로 자동 비행시킵니다:
     * - 목표 지점의 위도, 경도, 고도 설정
     * - 비행 속도 및 방향 제어
     * - 비행 진행 상황 모니터링
     * 
     * 실행 전 드론의 모드(MANUAL)와 비행 권한을 검증합니다.
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @param param 특정 지점 비행 파라미터 (목표 좌표, 고도, 속도 등)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     */
    HttpResultResponse flyToPoint(String sn, FlyToPointParam param);

    /**
     * 드론의 목표 지점 비행 미션을 종료합니다.
     * 
     * 이 메서드는 현재 진행 중인 특정 지점 비행을 즉시 중지시킵니다:
     * - 비행 중인 드론을 현재 위치에서 정지
     * - 비행 미션 완전 종료
     * - 안전한 상태로 전환
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     */
    HttpResultResponse flyToPointStop(String sn);

    /**
     * 목표 지점 비행 진행 상황 결과 알림을 처리합니다.
     * 
     * 이 메서드는 비행 중 실시간으로 전송되는 진행 상황을 처리합니다:
     * - 비행 진행률 업데이트
     * - 비행 상태 변경 알림
     * - 오류 발생 시 알림
     * 
     * @param receiver 수신자 정보
     * @param headers 메시지 헤더 정보
     * @return 처리 결과
     */
//    CommonTopicReceiver handleFlyToPointProgress(CommonTopicReceiver receiver, MessageHeaders headers);

    /**
     * 드론을 특정 지점으로 이륙시킵니다.
     * 
     * 이 메서드는 드론을 지정된 좌표로 직접 이륙시킵니다:
     * - 이륙 목표 지점 설정 (위도, 경도, 고도)
     * - 이륙 고도 및 속도 제어
     * - 이륙 진행 상황 모니터링
     * 
     * 실행 전 도크의 모드(IDLE)와 비행 권한을 검증합니다.
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @param param 특정 지점 이륙 파라미터 (목표 좌표, 고도, 속도 등)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     */
    HttpResultResponse takeoffToPoint(String sn, TakeoffToPointParam param);

    /**
     * 드론의 비행 제어 권한 또는 페이로드 제어 권한을 획득합니다.
     * 
     * 이 메서드는 드론을 제어하기 위한 권한을 획득합니다:
     * - FLIGHT: 비행 제어 권한 (이륙, 착륙, 비행 등)
     * - PAYLOAD: 페이로드 제어 권한 (카메라, 짐벌 등)
     * 
     * 권한이 이미 보유하고 있는 경우 즉시 성공을 반환하고,
     * 권한이 없는 경우 SDK를 통해 권한 획득을 시도합니다.
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @param authority 권한 타입 (FLIGHT 또는 PAYLOAD)
     * @param param 드론 페이로드 파라미터 (PAYLOAD 권한 시 필요)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     */
    HttpResultResponse seizeAuthority(String sn, DroneAuthorityEnum authority, DronePayloadParam param);

    /**
     * 드론의 페이로드를 제어합니다.
     * 
     * 이 메서드는 드론의 페이로드 장치를 제어합니다:
     * - 카메라 모드 전환 (사진/영상)
     * - 사진 촬영 및 영상 녹화
     * - 카메라 조준 및 초점 조정
     * - 짐벌 제어 (회전, 리셋 등)
     * 
     * 실행 전 페이로드 권한과 실행 조건을 검증합니다.
     * 
     * @param param 페이로드 명령 파라미터 (명령 타입, 페이로드 데이터 등)
     * @return HTTP 응답 결과 (성공/실패 및 메시지)
     * @throws Exception 리플렉션을 통한 핸들러 생성 중 오류 발생 시
     */
    HttpResultResponse payloadCommands(PayloadCommandsParam param) throws Exception;
}
