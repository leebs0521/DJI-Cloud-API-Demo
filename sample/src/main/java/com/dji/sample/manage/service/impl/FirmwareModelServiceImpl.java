package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.dao.IFirmwareModelMapper;
import com.dji.sample.manage.model.dto.FirmwareModelDTO;
import com.dji.sample.manage.model.entity.FirmwareModelEntity;
import com.dji.sample.manage.service.IFirmwareModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 펌웨어 모델 관리 서비스 구현체
 * 
 * DJI Cloud API의 펌웨어 모델 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 펌웨어 모델 매핑 관리
 *    - 펌웨어와 디바이스 모델 간의 매핑 관계 관리
 *    - 펌웨어 호환 디바이스 목록 저장
 *    - 펌웨어-디바이스 관계 추적
 *    - 호환성 정보 관리
 * 
 * 2. 데이터 변환 및 매핑
 *    - DTO를 엔티티로 변환
 *    - 펌웨어 모델 정보 정규화
 *    - 디바이스명 목록 처리
 *    - 데이터 유효성 검증
 * 
 * 3. 트랜잭션 관리
 *    - 데이터베이스 트랜잭션 관리
 *    - 데이터 일관성 보장
 *    - 롤백 처리
 *    - 동시성 제어
 * 
 * 주요 의존성:
 * - IFirmwareModelMapper: 펌웨어 모델 데이터베이스 접근
 * - FirmwareModelDTO: 펌웨어 모델 DTO
 * - FirmwareModelEntity: 펌웨어 모델 엔티티
 * 
 * 이 클래스는 DJI 펌웨어와 디바이스 모델 간의
 * 호환성 관계를 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/21
 */
@Service
@Transactional
public class FirmwareModelServiceImpl implements IFirmwareModelService {

    @Autowired
    private IFirmwareModelMapper mapper;

    /**
     * 펌웨어 디바이스명을 저장합니다.
     * 
     * 펌웨어와 호환되는 디바이스 모델 목록을 데이터베이스에 저장합니다.
     * 
     * @param firmwareModel 펌웨어 모델 정보
     */
    @Override
    public void saveFirmwareDeviceName(FirmwareModelDTO firmwareModel) {
        dto2Entity(firmwareModel).forEach(entity -> mapper.insert(entity));
    }

    /**
     * 펌웨어 모델 DTO를 엔티티 목록으로 변환합니다.
     * 
     * @param dto 펌웨어 모델 DTO
     * @return 펌웨어 모델 엔티티 목록
     */
    private List<FirmwareModelEntity> dto2Entity(FirmwareModelDTO dto) {
        if (Objects.isNull(dto) || CollectionUtils.isEmpty(dto.getDeviceNames())) {
            return Collections.EMPTY_LIST;
        }
        return dto.getDeviceNames().stream()
                .map(deviceName -> FirmwareModelEntity.builder()
                        .firmwareId(dto.getFirmwareId())
                        .deviceName(deviceName).build())
                .collect(Collectors.toList());
    }
}
