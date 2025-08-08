package com.dji.sdk.cloudapi.map;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 요소 생성 응답 데이터 클래스
 * 
 * 이 클래스는 지도 요소 생성 API의 응답 데이터를 정의합니다.
 * 지도에 새로운 요소(점, 선, 다각형 등)를 생성한 후,
 * 생성된 요소의 식별자 정보를 반환합니다.
 * 
 * 주요 구성 요소:
 * - id: 생성된 요소의 고유 식별자 (UUID 형식)
 * 
 * 이 클래스는 지도 요소 생성 API의 성공 응답으로 사용되며,
 * 클라이언트가 생성된 요소를 추적하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/16
 */
@Schema(description = "Create element response data")
public class CreateMapElementResponse {

    /**
     * 생성된 요소의 고유 식별자
     * 
     * UUID 형식으로 생성되며, 지도에서 해당 요소를 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     * 이 ID는 요소의 수정, 삭제, 조회 등 후속 작업에서 사용됩니다.
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "element id", format = "uuid")
    private String id;

    public CreateMapElementResponse() {
    }

    @Override
    public String toString() {
        return "CreateMapElementResponse{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public CreateMapElementResponse setId(String id) {
        this.id = id;
        return this;
    }
}
