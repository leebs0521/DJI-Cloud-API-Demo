package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 점 지오메트리 클래스
 * 
 * 이 클래스는 지도에서 단일 점을 표현하는 지오메트리입니다.
 * 드론의 착륙 지점, 경유지, 관심 지점 등을 표현할 때 사용됩니다.
 * 
 * 주요 특징:
 * - 단일 좌표점을 표현 (경도, 위도, 고도)
 * - GeoJSON Point 형식을 따름
 * - 최소 2개(경도, 위도), 최대 3개(경도, 위도, 고도) 좌표 필요
 * 
 * 사용 예시:
 * - 드론 착륙 지점 표시
 * - 비행 경로의 경유지
 * - 관심 지점 (POI) 표시
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "point geometry")
public class ElementPointGeometry extends ElementGeometryType {

    /**
     * 지오메트리 타입
     * 항상 "Point"로 설정되어 점 지오메트리임을 나타냅니다.
     */
    @Schema(example = "Point")
    @NotNull
    private final String type = ElementResourceTypeEnum.POINT.getTypeName();

    /**
     * 좌표 배열
     * [경도, 위도] 또는 [경도, 위도, 고도] 형태의 배열입니다.
     * 최소 2개(경도, 위도), 최대 3개(경도, 위도, 고도) 좌표를 포함합니다.
     * 예: [127.0, 37.5, 100.0] (서울, 고도 100m)
     */
    @Schema(example = "[113.943109, 22.577378]")
    @NotNull
    @Size(min = 2, max = 3)
    private Double[] coordinates;

    public ElementPointGeometry() {
        super();
    }

    /**
     * 좌표 배열을 ElementCoordinate 객체 리스트로 변환합니다.
     * 
     * 이 메서드는 점 지오메트리의 좌표 배열을 ElementCoordinate 객체로 변환합니다.
     * 고도 정보가 있는 경우(3개 좌표)와 없는 경우(2개 좌표)를 모두 처리합니다.
     * 
     * @return 좌표 리스트 (단일 ElementCoordinate 객체 포함)
     */
    @Override
    public List<ElementCoordinate> convertToList() {
        List<ElementCoordinate> coordinateList = new ArrayList<>();
        coordinateList.add(new ElementCoordinate()
                .setLongitude(this.coordinates[0])
                .setLatitude(this.coordinates[1])
                .setAltitude(this.coordinates.length == 3 ? this.coordinates[2] : null));
        return coordinateList;
    }

    /**
     * ElementCoordinate 객체 리스트를 좌표 배열로 변환합니다.
     * 
     * 이 메서드는 ElementCoordinate 객체 리스트를 점 지오메트리에 맞는 좌표 배열로 변환합니다.
     * 점 지오메트리는 단일 좌표만 필요하므로 첫 번째 좌표만 사용합니다.
     * 
     * @param coordinateList 변환할 좌표 리스트
     * @throws CloudSDKException 좌표 리스트가 비어있는 경우
     */
    @Override
    public void adapterCoordinateType(List<ElementCoordinate> coordinateList) {
        if (CollectionUtils.isEmpty(coordinateList)) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        this.coordinates = new Double[]{
                coordinateList.get(0).getLongitude(),
                coordinateList.get(0).getLatitude(),
                coordinateList.get(0).getAltitude()
        };
    }

    @Override
    public String toString() {
        return "ElementPointGeometry{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public ElementPointGeometry setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }
}
