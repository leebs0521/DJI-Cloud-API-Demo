package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dji.sample.manage.dao.IDeviceDictionaryMapper;
import com.dji.sample.manage.model.dto.DeviceDictionaryDTO;
import com.dji.sample.manage.model.entity.DeviceDictionaryEntity;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 디바이스 사전 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 사전 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 디바이스 사전 정보 조회
 *    - 도메인, 디바이스 타입, 서브타입 기반 사전 정보 조회
 *    - 디바이스 사전 정보의 정확한 매칭
 *    - 사전 정보의 유효성 검증
 *    - 단일 사전 정보 조회 최적화
 * 
 * 2. 디바이스 메타데이터 관리
 *    - 디바이스 이름 정보 관리
 *    - 디바이스 설명 정보 관리
 *    - 디바이스 타입 및 도메인 정보 관리
 *    - 서브타입 정보 관리
 * 
 * 3. 데이터베이스 연동
 *    - MyBatis-Plus를 통한 데이터베이스 접근
 *    - Lambda 쿼리 래퍼를 통한 타입 안전한 쿼리
 *    - 트랜잭션 관리 및 데이터 일관성 보장
 *    - 데이터베이스 엔티티와 DTO 간 변환
 * 
 * 4. 데이터 변환 및 매핑
 *    - Entity를 DTO로 변환
 *    - 디바이스 사전 정보의 정규화
 *    - 빌더 패턴을 통한 객체 생성
 *    - null 안전한 데이터 처리
 * 
 * 5. 쿼리 최적화
 *    - 조건부 쿼리 실행
 *    - 단일 결과 조회 최적화
 *    - 인덱스 활용을 위한 쿼리 구조
 *    - 성능 최적화된 데이터 접근
 * 
 * 주요 의존성:
 * - IDeviceDictionaryMapper: 디바이스 사전 데이터베이스 접근
 * - DeviceDictionaryEntity: 디바이스 사전 데이터베이스 엔티티
 * - DeviceDictionaryDTO: 디바이스 사전 데이터 전송 객체
 * - LambdaQueryWrapper: MyBatis-Plus 쿼리 래퍼
 * 
 * 이 클래스는 DJI 디바이스의 사전 정보를
 * 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/15
 */
@Service
@Transactional
public class DeviceDictionaryServiceImpl implements IDeviceDictionaryService {

    /**
     * 디바이스 사전 데이터베이스 매퍼
     * 디바이스 사전 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IDeviceDictionaryMapper mapper;

    /**
     * 도메인, 디바이스 타입, 서브타입으로 디바이스 사전 정보를 조회합니다.
     * 
     * 주어진 도메인, 디바이스 타입, 서브타입을 기반으로
     * 데이터베이스에서 해당하는 디바이스 사전 정보를 조회합니다.
     * 모든 매개변수가 null이 아닌 경우에만 조회를 수행하며,
     * 단일 결과만 반환하도록 최적화되어 있습니다.
     * 
     * @param domain 디바이스 도메인 (예: DOCK, DRONE, RC 등)
     * @param deviceType 디바이스 타입
     * @param subType 디바이스 서브타입
     * @return 디바이스 사전 정보 (Optional)
     */
    @Override
    public Optional<DeviceDictionaryDTO> getOneDictionaryInfoByTypeSubType(Integer domain, Integer deviceType, Integer subType) {
        // 모든 매개변수가 null이 아닌지 검증
        if (domain == null || deviceType == null || subType == null) {
            return Optional.empty();
        }
        
        // Lambda 쿼리 래퍼를 사용하여 타입 안전한 쿼리 구성
        return Optional.ofNullable(
                entityConvertToDTO(
                        mapper.selectOne(
                                new LambdaQueryWrapper<DeviceDictionaryEntity>()
                                        .eq(DeviceDictionaryEntity::getDomain, domain)           // 도메인 조건
                                        .eq(DeviceDictionaryEntity::getDeviceType, deviceType)   // 디바이스 타입 조건
                                        .eq(DeviceDictionaryEntity::getSubType, subType)         // 서브타입 조건
                                        .last(" limit 1 "))));                                   // 단일 결과 제한
    }

    /**
     * 데이터베이스 엔티티 객체를 디바이스 사전 데이터 전송 객체로 변환합니다.
     * 
     * DeviceDictionaryEntity 객체를 DeviceDictionaryDTO로 변환하여
     * 비즈니스 로직에서 사용할 수 있는 형태로 만듭니다.
     * 엔티티가 null인 경우에도 안전하게 처리되며,
     * 빌더 패턴을 사용하여 객체를 생성합니다.
     * 
     * @param entity 변환할 디바이스 사전 엔티티 객체
     * @return 변환된 DeviceDictionaryDTO 객체
     */
    private DeviceDictionaryDTO entityConvertToDTO(DeviceDictionaryEntity entity) {
        // DeviceDictionaryDTO 빌더 생성
        DeviceDictionaryDTO.DeviceDictionaryDTOBuilder builder = DeviceDictionaryDTO.builder();

        // 엔티티가 null이 아닌 경우에만 데이터 설정
        if (entity != null) {
            builder.deviceName(entity.getDeviceName())     // 디바이스 이름
                    .deviceDesc(entity.getDeviceDesc())    // 디바이스 설명
                    .deviceType(entity.getDeviceType())    // 디바이스 타입
                    .domain(entity.getDomain())            // 디바이스 도메인
                    .subType(entity.getSubType());         // 디바이스 서브타입
        }
        
        // 완성된 DeviceDictionaryDTO 객체 반환
        return builder.build();
    }
}