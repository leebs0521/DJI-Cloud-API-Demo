package com.dji.sample.wayline.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DJI Cloud API 웨이라인 파일 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 웨이라인 파일의 정보를 클라이언트와 서버 간에 전송하기 위한 데이터 전송 객체입니다.
 * 웨이라인 파일은 드론의 자동 비행 경로를 정의하는 KMZ 형식의 파일로,
 * DJI Pilot에서 업로드된 웨이라인 파일의 메타데이터와 속성 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일의 기본 정보 전송 (이름, ID, 드론 모델 등)
 * - 웨이라인 파일의 메타데이터 전송 (페이로드 모델, 템플릿 타입 등)
 * - 웨이라인 파일의 사용자 관련 정보 전송 (업로더, 즐겨찾기 상태 등)
 * - JSON 직렬화/역직렬화를 위한 어노테이션 지원
 * 
 * 사용 시나리오:
 * - 웨이라인 파일 목록 조회 시 응답 데이터로 사용
 * - 웨이라인 파일 업로드 완료 후 클라이언트에 전송
 * - 웨이라인 파일 상세 정보 조회 시 사용
 * - 웨이라인 작업 생성 시 참조 파일 정보로 사용
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineFileDTO {

    /**
     * 웨이라인 파일명
     * 사용자가 업로드한 웨이라인 파일의 원본 파일명
     */
    private String name;

    /**
     * 웨이라인 파일 고유 ID
     * 시스템에서 생성하는 웨이라인 파일의 고유 식별자
     * JSON 직렬화 시 "id" 필드명으로 매핑
     */
    @JsonProperty("id")
    private String waylineId;

    /**
     * 드론 모델 키
     * 이 웨이라인이 지원하는 드론 모델의 식별자
     * 예: "0-67-0", "0-67-1" 등
     */
    private String droneModelKey;

    /**
     * 웨이라인 파일 서명 (해시값)
     * 파일의 무결성을 검증하기 위한 해시값
     * 파일 내용이 변경되었는지 확인하는 용도
     */
    private String sign;

    /**
     * 페이로드 모델 키 목록
     * 이 웨이라인이 지원하는 페이로드 장비들의 식별자 목록
     * 예: ["0-67-0", "0-67-1"] (카메라, 센서 등)
     */
    private List<String> payloadModelKeys;

    /**
     * 즐겨찾기 여부
     * 현재 사용자가 이 웨이라인을 즐겨찾기로 설정했는지 여부
     * true: 즐겨찾기 설정됨, false: 즐겨찾기 해제됨
     */
    private Boolean favorited;

    /**
     * 템플릿 타입 목록
     * 이 웨이라인이 지원하는 템플릿 타입들의 식별자 목록
     * 예: [1, 2, 3] (정찰, 측량, 모니터링 등)
     */
    private List<Integer> templateTypes;

    /**
     * 클라우드 스토리지 객체 키
     * 웨이라인 파일이 저장된 클라우드 스토리지의 객체 키
     * 파일 다운로드 시 이 키를 사용하여 파일에 접근
     */
    private String objectKey;

    /**
     * 업로드한 사용자명
     * 이 웨이라인 파일을 업로드한 사용자의 이름
     * JSON 직렬화 시 "user_name" 필드명으로 매핑
     */
    @JsonProperty("user_name")
    private String username;

    /**
     * 최종 수정 시간
     * 웨이라인 파일 정보가 마지막으로 수정된 시간 (Unix timestamp)
     * 밀리초 단위의 타임스탬프 값
     */
    private Long updateTime;
}
