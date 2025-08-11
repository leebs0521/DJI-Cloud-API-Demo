package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.manage.model.entity.DeviceFirmwareEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 디바이스 펌웨어 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 펌웨어 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 정보 관리
 *    - 펌웨어 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 펌웨어 ID 기반 조회
 *    - 펌웨어 버전 및 상태 관리
 * 
 * 2. 펌웨어 모델 연동
 *    - 펌웨어와 디바이스 모델 간의 관계 관리
 *    - 디바이스 모델별 펌웨어 호환성 정보
 *    - 펌웨어 지원 디바이스 목록 관리
 * 
 * 3. 펌웨어 검색 및 필터링
 *    - 디바이스 모델명 기반 펌웨어 검색
 *    - 펌웨어 상태별 필터링
 *    - 펌웨어 버전별 정렬 및 조회
 * 
 * 4. 고급 쿼리 기능
 *    - 복잡한 JOIN 쿼리를 통한 펌웨어-모델 연동 조회
 *    - 동적 SQL을 통한 조건부 검색
 *    - 페이지네이션을 통한 대용량 데이터 처리
 * 
 * 이 인터페이스는 DeviceFirmwareEntity와 연동되어 펌웨어 테이블의
 * 모든 데이터베이스 작업을 처리하며, 펌웨어 모델 테이블과의
 * JOIN을 통해 상세한 펌웨어 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
public interface IDeviceFirmwareMapper extends BaseMapper<DeviceFirmwareEntity> {
    
    /**
     * 펌웨어와 디바이스 모델을 JOIN하여 펌웨어 정보를 조회하는 SQL 쿼리
     * 
     * 이 SQL은 다음과 같은 기능을 제공합니다:
     * - 펌웨어 테이블과 펌웨어 모델 테이블을 JOIN
     * - 디바이스 모델명을 GROUP_CONCAT으로 결합
     * - 동적 조건을 통한 디바이스 모델명 필터링
     * - 펌웨어 ID별로 그룹화하여 중복 제거
     */
    String sql = "<script> \n" +
                "SELECT \n" +
                "  * \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      a.*, \n" +
                "      group_concat(b.device_name) device_name \n" +
                "    from \n" +
                "      manage_device_firmware a \n" +
                "      join manage_firmware_model b on a.firmware_id = b.firmware_id \n" +
                "   <if test='device_name != null and device_name != \"\"'> \n" +
                "       and b.device_name = #{device_name} \n" +
                "   </if> \n" +
                "   group by firmware_id \n" +
                "  ) c ${ew.customSqlSegment} \n";

    /**
     * 펌웨어 정보를 페이지네이션으로 조회합니다.
     * 
     * 이 메서드는 펌웨어와 디바이스 모델 정보를 JOIN하여
     * 페이지 단위로 펌웨어 정보를 조회합니다.
     * 
     * @param page 페이지 정보 (페이지 번호, 페이지 크기 등)
     * @param wrapper MyBatis-Plus 조건 래퍼 (추가 검색 조건)
     * @param deviceName 디바이스 모델명 (선택적 필터링 조건)
     * @return 펌웨어 정보 페이지 (펌웨어 모델 정보 포함)
     */
    @Select(sql + "</script>")
    Page<DeviceFirmwareEntity> selectPage(Page page, @Param(Constants.WRAPPER)Wrapper<DeviceFirmwareEntity> wrapper, @Param("device_name") String deviceName);

    /**
     * 펌웨어 정보를 단일 레코드로 조회합니다.
     * 
     * 이 메서드는 펌웨어와 디바이스 모델 정보를 JOIN하여
     * 조건에 맞는 단일 펌웨어 정보를 조회합니다.
     * 
     * @param wrapper MyBatis-Plus 조건 래퍼 (검색 조건)
     * @param deviceName 디바이스 모델명 (선택적 필터링 조건)
     * @return 펌웨어 정보 (펌웨어 모델 정보 포함)
     */
    @Select(sql + " limit 1 </script>")
    DeviceFirmwareEntity selectOne(@Param(Constants.WRAPPER)Wrapper<DeviceFirmwareEntity> wrapper, @Param("device_name") String deviceName);
}
