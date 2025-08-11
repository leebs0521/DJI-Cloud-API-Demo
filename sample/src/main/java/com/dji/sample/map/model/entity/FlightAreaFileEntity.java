package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 비행 영역 파일 엔티티
 * 
 * DJI Cloud API의 비행 영역 파일 메타데이터를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 비행 영역 파일의 기본 정보와 저장소 정보를 관리합니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: flight_area_file
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 파일 기본 정보
 *    - 파일 ID (fileId): 파일의 고유 식별자
 *    - 워크스페이스 ID (workspaceId): 파일이 속한 워크스페이스
 *    - 파일 이름 (name): 사용자 친화적인 파일명
 * 
 * 3. 파일 저장소 정보
 *    - 객체 키 (objectKey): 클라우드 저장소에서의 파일 식별 키
 *    - 서명 (sign): 파일 접근을 위한 보안 서명
 *    - 파일 크기 (size): 파일 크기 (바이트)
 * 
 * 4. 파일 상태 정보
 *    - 최신 버전 여부 (latest): 현재 워크스페이스의 최신 버전 여부
 * 
 * 5. 메타데이터 정보
 *    - 생성 시간 (createTime): 자동 생성
 *    - 수정 시간 (updateTime): 자동 업데이트
 * 
 * 6. MyBatis-Plus 어노테이션 활용
 *    - @TableName: 데이터베이스 테이블명 지정
 *    - @TableId: 기본 키 필드 지정 및 자동 증가 설정
 *    - @TableField: 데이터베이스 컬럼명 매핑 및 자동 필드 채우기
 * 
 * 7. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 파일 메타데이터 데이터베이스 저장
 * - 파일 시스템 연동 관리
 * - 파일 버전 관리
 * - 파일 접근 권한 관리
 * 
 * 사용 예시:
 * - 비행 영역 파일 업로드/다운로드 관리
 * - 파일 버전 추적
 * - 파일 접근 URL 생성
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@TableName("flight_area_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightAreaFileEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 비행 영역 파일 레코드의 고유 식별자입니다.
     * 데이터베이스에서 자동으로 증가하는 정수 값으로 설정됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableId(type = IdType.AUTO): 자동 증가 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 파일 ID
     * 
     * 비행 영역 파일의 고유 식별자입니다.
     * 시스템에서 파일을 구분하는 데 사용되는 문자열 식별자입니다.
     * 
     * 데이터베이스 컬럼: file_id
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 워크스페이스 ID
     * 
     * 파일이 속한 워크스페이스의 고유 식별자입니다.
     * 워크스페이스별로 파일을 관리할 수 있습니다.
     * 
     * 데이터베이스 컬럼: workspace_id
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 파일 이름
     * 
     * 사용자가 설정한 파일의 이름입니다.
     * 파일 식별을 위한 사용자 친화적인 이름으로 사용됩니다.
     * 
     * 데이터베이스 컬럼: name
     */
    @TableField("name")
    private String name;

    /**
     * 객체 키
     * 
     * 클라우드 저장소(예: AWS S3, OSS)에서 파일을 식별하는 키입니다.
     * 파일의 실제 저장 위치를 나타냅니다.
     * 
     * 데이터베이스 컬럼: object_key
     */
    @TableField("object_key")
    private String objectKey;

    /**
     * 서명
     * 
     * 파일 접근을 위한 보안 서명 정보입니다.
     * 임시 URL 생성이나 파일 다운로드 시 사용됩니다.
     * 
     * 데이터베이스 컬럼: sign
     */
    @TableField("sign")
    private String sign;

    /**
     * 파일 크기
     * 
     * 파일의 크기를 바이트 단위로 나타냅니다.
     * 파일 업로드/다운로드 진행률 계산에 사용됩니다.
     * 
     * 데이터베이스 컬럼: size
     */
    @TableField("size")
    private Integer size;

    /**
     * 최신 버전 여부
     * 
     * 해당 파일이 현재 워크스페이스의 최신 버전인지 여부를 나타냅니다.
     * true: 최신 버전, false: 이전 버전
     * 
     * 데이터베이스 컬럼: latest
     */
    @TableField("latest")
    private Boolean latest;

    /**
     * 생성 시간
     * 
     * 비행 영역 파일이 생성된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT): 삽입 시 자동 생성
     * 
     * 데이터베이스 컬럼: create_time
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 
     * 비행 영역 파일이 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT_UPDATE): 삽입/수정 시 자동 업데이트
     * 
     * 데이터베이스 컬럼: update_time
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
