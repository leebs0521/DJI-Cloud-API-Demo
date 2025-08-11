package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 워크스페이스 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 워크스페이스 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 기본 정보 관리
 *    - 워크스페이스 ID 및 이름 관리
 *    - 워크스페이스 설명 및 플랫폼 정보 관리
 *    - 워크스페이스 식별자 및 바인딩 코드 관리
 * 
 * 2. 워크스페이스 식별 정보 관리
 *    - 데이터베이스 내부 ID 관리
 *    - 고유한 워크스페이스 ID 관리
 *    - 워크스페이스 바인딩 코드 관리
 * 
 * 3. 워크스페이스 메타데이터 관리
 *    - 워크스페이스 설명 정보 관리
 *    - 플랫폼 이름 정보 관리
 *    - 워크스페이스 표시 정보 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 워크스페이스의 모든 정보를 포함하며, 사용자 그룹의
 * 작업 공간을 관리하는 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/22
 * @version 0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceDTO {

    /**
     * 워크스페이스 내부 ID
     * 데이터베이스에서 워크스페이스를 식별하는 내부 ID
     */
    private Integer id;

    /**
     * 워크스페이스 ID
     * 워크스페이스를 고유하게 식별하는 외부 ID
     */
    private String workspaceId;

    /**
     * 워크스페이스 이름
     * 워크스페이스의 표시 이름
     */
    private String workspaceName;

    /**
     * 워크스페이스 설명
     * 워크스페이스에 대한 추가 설명 정보
     */
    private String workspaceDesc;

    /**
     * 플랫폼 이름
     * 워크스페이스가 속한 플랫폼의 이름
     */
    private String platformName;

    /**
     * 바인딩 코드
     * 워크스페이스 바인딩을 위한 고유 코드
     */
    private String bindCode;
}