package com.dji.sample.map.model.param;
import com.dji.sample.map.model.dto.FlightAreaContent;
import com.dji.sdk.cloudapi.flightarea.GeofenceTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 비행 영역 생성 요청 파라미터
 * 
 * DJI Cloud API에서 비행 영역을 생성하기 위한 HTTP POST 요청의 파라미터 클래스입니다.
 * 이 클래스는 비행 영역 생성에 필요한 모든 필수 정보를 포함합니다.
 * 
 * 1. 비행 영역 기본 정보
 *    - 비행 영역 ID (id): 고유 식별자
 *    - 비행 영역 이름 (name): 사용자 친화적인 이름
 *    - 비행 영역 타입 (type): 분류 타입
 * 
 * 2. 비행 영역 상세 내용
 *    - 비행 영역 내용 (content): 속성 및 기하학적 정보
 * 
 * 3. 데이터 검증
 *    - @NotNull: 모든 필드가 필수 입력 사항
 *    - @Valid: 중첩된 객체의 유효성 검증
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 생성 API 요청 파라미터
 * - 비행 영역 데이터 검증
 * - REST API 요청 본문 구조화
 * 
 * 사용 예시:
 * - 비행 영역 생성 API 호출
 * - 비행 영역 데이터 유효성 검사
 * - 클라이언트 요청 데이터 매핑
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Schema(description = "비행 영역 생성 요청 파라미터")
public class PostFlightAreaParam {
    /**
     * 비행 영역 ID
     * 
     * 생성할 비행 영역의 고유 식별자입니다.
     * 시스템에서 비행 영역을 구분하는 데 사용되는 기본 키입니다.
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     */
    @NotNull
    @Schema(description = "비행 영역의 고유 식별자")
    private String id;

    /**
     * 비행 영역 이름
     * 
     * 생성할 비행 영역의 사용자 친화적인 이름입니다.
     * 비행 영역 식별을 위한 표시명으로 사용됩니다.
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     */
    @NotNull
    @Schema(description = "비행 영역의 사용자 친화적인 이름")
    private String name;

    /**
     * 비행 영역 타입
     * 
     * 생성할 비행 영역의 분류 타입입니다.
     * 예: RESTRICTED_AREA (제한 구역), WARNING_AREA (경고 구역) 등
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     */
    @NotNull
    @Schema(description = "비행 영역의 분류 타입 (DFENCE: 동적 펜스, NFZ: 비행 금지 구역)")
    private GeofenceTypeEnum type;

    /**
     * 비행 영역 상세 내용
     * 
     * 생성할 비행 영역의 속성과 기하학적 정보를 포함하는 상세 내용입니다.
     * 비행 영역의 실제 형태와 설정 정보가 포함됩니다.
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     * - @Valid: 중첩된 FlightAreaContent 객체의 유효성 검증 수행
     */
    @NotNull
    @Valid
    @Schema(description = "비행 영역의 속성과 기하학적 정보를 포함하는 상세 내용")
    private FlightAreaContent content;
}
