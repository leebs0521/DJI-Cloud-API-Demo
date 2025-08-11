package com.dji.sample.control.service;

import com.dji.sample.control.model.dto.JwtAclDTO;
import com.dji.sample.control.model.param.DrcConnectParam;
import com.dji.sample.control.model.param.DrcModeParam;
import com.dji.sdk.cloudapi.control.DrcModeMqttBroker;

/**
 * DRC 서비스 인터페이스
 * 
 * DRC(Direct Remote Control) 모드 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 정의합니다:
 * 
 * 1. DRC 모드 상태 관리
 *    - Redis를 통한 DRC 모드 상태 저장/조회/삭제
 *    - DRC 모드 진입/종료 시 상태 동기화
 *    - 클라이언트별 DRC 모드 상태 추적
 * 
 * 2. 사용자 인증 및 권한 관리
 *    - MQTT ACL 권한 설정
 *    - JWT 토큰 기반 사용자 인증
 *    - 클라이언트별 권한 관리
 *    - 토큰 만료 시간 관리
 * 
 * 3. DRC 모드 진입/종료 처리
 *    - DRC 모드 진입 조건 검증
 *    - 웨이라인 작업 일시정지/재개
 *    - 비행 권한 획득
 *    - 드론 상태 확인 (공중 비행 여부)
 * 
 * 4. MQTT 브로커 설정
 *    - DRC 전용 MQTT 브로커 구성
 *    - 토픽 기반 권한 설정
 *    - 실시간 통신 채널 제공
 *    - 보안 연결 설정
 * 
 * DRC 모드는 사용자가 직접 드론을 실시간으로 제어할 수 있는 모드로,
 * 안전한 원격 조종을 위한 다양한 검증과 권한 관리 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
public interface IDrcService {

    /**
     * 도크의 DRC 모드를 Redis에 저장합니다.
     * 
     * 이 메서드는 도크가 DRC 모드에 진입했을 때 클라이언트 정보를 Redis에 저장합니다:
     * - 클라이언트 ID와 도크 SN을 매핑하여 저장
     * - 만료 시간 설정 (DRC_MODE_ALIVE_SECOND)
     * - 중복 진입 방지를 위한 상태 관리
     * 
     * @param dockSn 도크 시리얼 번호
     * @param clientId 클라이언트 ID (사용자 식별자)
     */
    void setDrcModeInRedis(String dockSn, String clientId);

    /**
     * 도크를 제어하고 있는 클라이언트를 조회합니다.
     * 
     * 이 메서드는 현재 도크를 DRC 모드로 제어하고 있는 클라이언트를 조회합니다:
     * - Redis에서 도크 SN에 해당하는 클라이언트 ID 조회
     * - DRC 모드 상태 확인
     * - 클라이언트 권한 검증
     * 
     * @param dockSn 도크 시리얼 번호
     * @return 클라이언트 ID (제어 중인 클라이언트가 없으면 null)
     */
    String getDrcModeInRedis(String dockSn);

    /**
     * 도크의 DRC 모드를 Redis에서 삭제합니다.
     * 
     * 이 메서드는 도크가 DRC 모드에서 종료될 때 Redis의 상태 정보를 삭제합니다:
     * - 클라이언트와 도크의 매핑 정보 삭제
     * - DRC 모드 상태 초기화
     * - 다른 클라이언트의 진입 허용
     * 
     * @param dockSn 도크 시리얼 번호
     * @return 삭제 성공 여부 (true: 성공, false: 실패)
     */
    Boolean delDrcModeInRedis(String dockSn);

    /**
     * 제어 터미널을 위한 MQTT 옵션을 제공합니다.
     * 
     * 이 메서드는 DRC 모드 진입을 위한 MQTT 브로커 설정을 제공합니다:
     * - 클라이언트별 고유 ID 생성
     * - MQTT ACL 권한 설정 (토픽 기반 접근 제어)
     * - JWT 토큰 생성 및 만료 시간 설정
     * - 보안 연결을 위한 브로커 정보 제공
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userId 사용자 ID
     * @param username 사용자명
     * @param param DRC 연결 파라미터 (클라이언트 ID, 만료 시간 등)
     * @return DRC 모드 MQTT 브로커 정보 (연결 정보, 토큰 등)
     */
    DrcModeMqttBroker userDrcAuth(String workspaceId, String userId, String username, DrcConnectParam param);

    /**
     * 도크가 DRC 모드에 진입하도록 합니다. 관련 권한을 부여합니다.
     * 
     * 이 메서드는 도크를 DRC 모드로 전환하고 필요한 권한을 설정합니다:
     * - DRC 모드 진입 조건 검증 (드론 상태, 웨이라인 작업 등)
     * - 비행 권한 획득
     * - MQTT 토픽 권한 설정
     * - Redis 상태 저장
     * - 웨이라인 작업 일시정지 (필요시)
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터 (도크 SN, 클라이언트 ID 등)
     * @return JWT ACL 정보 (토픽 권한 정보)
     */
    JwtAclDTO deviceDrcEnter(String workspaceId, DrcModeParam param);

    /**
     * 도크가 DRC 모드에서 종료하도록 합니다.
     * 
     * 이 메서드는 도크를 DRC 모드에서 종료하고 정상 상태로 복원합니다:
     * - DRC 모드 종료 명령 전송
     * - Redis 상태 정보 삭제
     * - MQTT ACL 권한 해제
     * - 웨이라인 작업 재개 (일시정지된 경우)
     * - 비행 권한 해제
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터 (도크 SN, 클라이언트 ID 등)
     */
    void deviceDrcExit(String workspaceId, DrcModeParam param);
}
