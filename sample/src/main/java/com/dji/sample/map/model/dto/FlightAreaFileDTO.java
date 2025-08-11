package com.dji.sample.map.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비행 영역 파일 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역 파일의 메타데이터를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역 파일의 기본 정보와 저장소 정보를 포함합니다.
 * 
 * 1. 파일 기본 정보
 *    - 파일 ID (fileId)
 *    - 워크스페이스 ID (workspaceId)
 *    - 파일 이름 (name)
 * 
 * 2. 파일 저장소 정보
 *    - 객체 저장소 키 (objectKey)
 *    - 서명 정보 (sign)
 *    - 파일 크기 (size)
 * 
 * 3. 파일 상태 정보
 *    - 최신 버전 여부 (latest)
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 파일 목록 조회
 * - 비행 영역 파일 메타데이터 관리
 * - 파일 다운로드 링크 생성
 * - 파일 버전 관리
 * 
 * 사용 예시:
 * - 비행 영역 파일 관리 대시보드
 * - 파일 업로드/다운로드 작업
 * - 파일 버전 추적
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaFileDTO {

    /**
     * 파일 ID
     * 
     * 비행 영역 파일의 고유 식별자입니다.
     * 시스템에서 파일을 구분하는 데 사용되는 기본 키입니다.
     */
    private String fileId;

    /**
     * 워크스페이스 ID
     * 
     * 파일이 속한 워크스페이스의 고유 식별자입니다.
     * 워크스페이스별로 파일을 관리할 수 있습니다.
     */
    private String workspaceId;

    /**
     * 파일 이름
     * 
     * 사용자가 설정한 파일의 이름입니다.
     * 파일 식별을 위한 사용자 친화적인 이름으로 사용됩니다.
     */
    private String name;

    /**
     * 객체 저장소 키
     * 
     * 클라우드 저장소(예: AWS S3, OSS)에서 파일을 식별하는 키입니다.
     * 파일의 실제 저장 위치를 나타냅니다.
     */
    private String objectKey;

    /**
     * 서명 정보
     * 
     * 파일 접근을 위한 보안 서명 정보입니다.
     * 임시 URL 생성이나 파일 다운로드 시 사용됩니다.
     */
    private String sign;

    /**
     * 파일 크기
     * 
     * 파일의 크기를 바이트 단위로 나타냅니다.
     * 파일 업로드/다운로드 진행률 계산에 사용됩니다.
     */
    private Integer size;

    /**
     * 최신 버전 여부
     * 
     * 해당 파일이 현재 워크스페이스의 최신 버전인지 여부를 나타냅니다.
     * true: 최신 버전
     * false: 이전 버전
     */
    private Boolean latest;
}
