package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.FirmwareModelDTO;

/**
 * 펌웨어 모델 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 펌웨어 모델 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어-디바이스 모델 관계 관리
 *    - 펌웨어 파일과 디바이스 모델 간의 관계 저장
 *    - 펌웨어 호환성 정보 관리
 *    - 디바이스 모델별 펌웨어 매핑
 * 
 * 2. 펌웨어 호환성 관리
 *    - 특정 디바이스 모델에 호환되는 펌웨어 관리
 *    - 펌웨어 업그레이드 호환성 확인
 *    - 디바이스 모델별 펌웨어 지원 정보
 * 
 * 3. 펌웨어 배포 관리
 *    - 디바이스 모델별 펌웨어 배포 정보 관리
 *    - 펌웨어 업그레이드 대상 디바이스 식별
 *    - 펌웨어 배포 전략 지원
 * 
 * 이 인터페이스는 DJI 디바이스의 펌웨어와 디바이스 모델 간의
 * 호환성을 체계적으로 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/21
 */
public interface IFirmwareModelService {

    /**
     * 펌웨어-디바이스 모델 관계 저장
     * 
     * 펌웨어 파일과 디바이스 모델 간의 관계를
     * 데이터베이스에 저장합니다.
     * 
     * @param firmwareModel 저장할 펌웨어 모델 관계 정보
     */
    void saveFirmwareDeviceName(FirmwareModelDTO firmwareModel);

}
