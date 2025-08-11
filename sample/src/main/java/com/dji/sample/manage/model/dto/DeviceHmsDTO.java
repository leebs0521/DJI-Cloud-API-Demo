package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 디바이스 HMS(Health Management System) 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 HMS 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 메시지 정보 관리
 *    - HMS 메시지 ID 및 식별자 관리
 *    - HMS 메시지 레벨 및 모듈 정보 관리
 *    - HMS 메시지 키 및 내용 관리
 * 
 * 2. 다국어 메시지 지원
 *    - 중국어 메시지 관리
 *    - 영어 메시지 관리
 *    - 언어별 HMS 메시지 분리 및 관리
 * 
 * 3. 디바이스 연결 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 바인딩 ID 및 타스크 ID 관리
 *    - 디바이스와의 연결 정보 추적
 * 
 * 4. 시간 정보 및 복제 기능
 *    - HMS 메시지 생성 및 수정 시간 관리
 *    - Cloneable 인터페이스 구현으로 객체 복제 지원
 *    - 안전한 객체 복제 및 예외 처리
 * 
 * 이 클래스는 디바이스의 건강 상태 모니터링 정보를 다국어로
 * 제공하며, HMS 메시지의 생성부터 처리까지의 전체
 * 라이프사이클을 관리하는 표준화된 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceHmsDTO implements Cloneable {

    /**
     * HMS 메시지 ID
     * HMS 메시지를 고유하게 식별하는 ID
     */
    private String hmsId;

    /**
     * 타스크 ID
     * HMS 메시지가 발생한 작업의 식별자
     */
    private String tid;

    /**
     * 바인딩 ID
     * 디바이스 바인딩과 관련된 식별자
     */
    private String bid;

    /**
     * 디바이스 시리얼 번호
     * HMS 메시지가 발생한 디바이스의 시리얼 번호
     */
    private String sn;

    /**
     * HMS 메시지 레벨
     * HMS 메시지의 중요도 레벨 (예: 1-경고, 2-오류, 3-심각 등)
     */
    private Integer level;

    /**
     * HMS 모듈
     * HMS 메시지가 발생한 디바이스 모듈의 식별자
     */
    private Integer module;

    /**
     * HMS 메시지 키
     * HMS 메시지를 식별하는 고유한 키
     */
    private String key;

    /**
     * 중국어 메시지
     * HMS 메시지의 중국어 버전
     */
    private String messageZh;

    /**
     * 영어 메시지
     * HMS 메시지의 영어 버전
     */
    private String messageEn;

    /**
     * 생성 시간
     * HMS 메시지가 생성된 시간
     */
    private LocalDateTime createTime;

    /**
     * 수정 시간
     * HMS 메시지가 마지막으로 수정된 시간
     */
    private LocalDateTime updateTime;

    /**
     * 현재 객체의 복사본을 생성합니다.
     * 
     * 이 메서드는 Cloneable 인터페이스를 구현하여 객체의
     * 안전한 복제를 제공합니다. 복제 실패 시 기본값으로
     * 새로운 객체를 생성하여 반환합니다.
     * 
     * @return 현재 객체의 복사본
     */
    @Override
    public DeviceHmsDTO clone() {
        try {
            return (DeviceHmsDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            return DeviceHmsDTO.builder()
                    .sn(this.sn)
                    .bid(this.bid)
                    .tid(this.tid)
                    .createTime(this.createTime)
                    .updateTime(this.updateTime)
                    .build();
        }
    }
}
