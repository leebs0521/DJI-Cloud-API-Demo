package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 펌웨어 모델 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 펌웨어 모델 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 모델 기본 정보 관리
 *    - 펌웨어 ID 관리
 *    - 펌웨어 모델 식별 정보 관리
 *    - 펌웨어 모델 메타데이터 관리
 * 
 * 2. 디바이스 호환성 정보 관리
 *    - 지원 디바이스 모델명 목록 관리
 *    - 펌웨어와 디바이스 간의 호환성 정보
 *    - 다중 디바이스 지원 정보 제공
 * 
 * 3. 펌웨어-디바이스 관계 관리
 *    - 펌웨어와 디바이스 모델 간의 매핑 관계
 *    - 펌웨어 지원 디바이스 목록 관리
 *    - 펌웨어 호환성 정보 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 펌웨어와 디바이스 모델 간의 호환성 정보를
 * 관리하며, 펌웨어가 지원하는 디바이스 목록을 제공하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirmwareModelDTO {

    /**
     * 펌웨어 ID
     * 펌웨어를 고유하게 식별하는 ID
     */
    private String firmwareId;

    /**
     * 지원 디바이스 모델명 목록
     * 이 펌웨어가 지원하는 디바이스 모델명들의 목록
     */
    private List<String> deviceNames;
}
