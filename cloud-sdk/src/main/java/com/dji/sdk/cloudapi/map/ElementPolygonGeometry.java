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
 * 다각형 지오메트리 클래스
 * 
 * 이 클래스는 지도에서 다각형(폴리곤)을 표현하는 지오메트리입니다.
 * 드론의 비행 금지 구역, 관심 구역, 착륙장 등을 표현할 때 사용됩니다.
 * 
 * 주요 특징:
 * - 여러 좌표점을 연결하여 닫힌 다각형을 표현
 * - GeoJSON Polygon 형식을 따름
 * - 최소 3개 이상의 좌표점 필요 (다각형을 그리려면 최소 3개의 꼭지점이 필요)
 * - 첫 번째 좌표와 마지막 좌표가 같아야 함 (닫힌 도형)
 * 
 * 사용 예시:
 * - 드론 비행 금지 구역 표시
 * - 관심 구역 (AOI) 표시
 * - 착륙장 경계 표시
 * - 비행 허가 구역 표시
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "polygon geometry")
public class ElementPolygonGeometry extends ElementGeometryType {

    /**
     * 지오메트리 타입
     * 항상 "Polygon"으로 설정되어 다각형 지오메트리임을 나타냅니다.
     */
    @Schema(example = "Polygon")
    @NotNull
    private final String type = ElementTypeEnum.POLYGON.getDesc();

    /**
     * 좌표 배열의 배열의 배열
     * 3차원 배열로 구성되어 있습니다:
     * - 첫 번째 차원: 다각형의 개수 (현재는 1개만 지원)
     * - 두 번째 차원: 다각형의 꼭지점들
     * - 세 번째 차원: 각 꼭지점의 [경도, 위도]
     * 예: [[[127.0, 37.5], [127.1, 37.5], [127.1, 37.6], [127.0, 37.6], [127.0, 37.5]]]
     */
    @Schema(example = "[[[113.943109, 22.577378]]]")
    @NotNull
    @Size(min = 1, max = 1)
    private Double[][][] coordinates;

    public ElementPolygonGeometry() {
        super();
    }

    /**
     * 좌표 배열을 ElementCoordinate 객체 리스트로 변환합니다.
     * 
     * 이 메서드는 다각형 지오메트리의 좌표 배열들을 ElementCoordinate 객체 리스트로 변환합니다.
     * 다각형을 그리려면 최소 3개 이상의 좌표점이 필요합니다.
     * 
     * @return 좌표 리스트 (여러 ElementCoordinate 객체 포함)
     * @throws CloudSDKException 좌표가 3개 미만인 경우
     */
    @Override
    public List<ElementCoordinate> convertToList() {
        if (this.coordinates[0].length < 3) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        List<ElementCoordinate> coordinateList = new ArrayList<>();
        for (Double[] coordinate : this.coordinates[0]) {
            coordinateList.add(new ElementCoordinate()
                    .setLongitude(coordinate[0])
                    .setLatitude(coordinate[1]));
        }
        return coordinateList;
    }

    /**
     * ElementCoordinate 객체 리스트를 좌표 배열로 변환합니다.
     * 
     * 이 메서드는 ElementCoordinate 객체 리스트를 다각형 지오메트리에 맞는 좌표 배열로 변환합니다.
     * 다각형을 그리려면 최소 3개 이상의 좌표점이 필요합니다.
     * 
     * @param coordinateList 변환할 좌표 리스트
     * @throws CloudSDKException 좌표 리스트가 비어있거나 3개 미만인 경우
     */
    @Override
    public void adapterCoordinateType(List<ElementCoordinate> coordinateList) {
        if (CollectionUtils.isEmpty(coordinateList) || coordinateList.size() < 3) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        this.coordinates = new Double[1][coordinateList.size()][2];
        for (int i = 0; i < this.coordinates[0].length; i++) {
            this.coordinates[0][i][0] = coordinateList.get(i).getLongitude();
            this.coordinates[0][i][1] = coordinateList.get(i).getLatitude();
        }
    }

    @Override
    public String toString() {
        return "ElementPolygonGeometry{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public Double[][][] getCoordinates() {
        return coordinates;
    }

    public ElementPolygonGeometry setCoordinates(Double[][][] coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }
}
