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
 * 선 지오메트리 클래스
 * 
 * 이 클래스는 지도에서 선을 표현하는 지오메트리입니다.
 * 드론의 비행 경로, 경계선, 도로 등을 표현할 때 사용됩니다.
 * 
 * 주요 특징:
 * - 여러 좌표점을 연결하여 선을 표현
 * - GeoJSON LineString 형식을 따름
 * - 최소 2개 이상의 좌표점 필요 (선을 그리려면 시작점과 끝점이 필요)
 * 
 * 사용 예시:
 * - 드론 비행 경로 표시
 * - 비행 금지 구역의 경계선
 * - 관심 구역의 경계선
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "line geometry")
public class ElementLineStringGeometry extends ElementGeometryType {

    /**
     * 지오메트리 타입
     * 항상 "LineString"으로 설정되어 선 지오메트리임을 나타냅니다.
     */
    @Schema(example = "LineString")
    @NotNull
    private final String type = ElementResourceTypeEnum.LINE_STRING.getTypeName();

    /**
     * 좌표 배열의 배열
     * 각 좌표는 [경도, 위도] 형태의 배열입니다.
     * 여러 좌표점을 연결하여 선을 만듭니다.
     * 예: [[127.0, 37.5], [127.1, 37.6], [127.2, 37.7]]
     */
    @Schema(example = "[[113.943109, 22.577378]]")
    @NotNull
    @Size(min = 2)
    private Double[][] coordinates;

    public ElementLineStringGeometry() {
        super();
    }

    /**
     * 좌표 배열을 ElementCoordinate 객체 리스트로 변환합니다.
     * 
     * 이 메서드는 선 지오메트리의 좌표 배열들을 ElementCoordinate 객체 리스트로 변환합니다.
     * 선을 그리려면 최소 2개 이상의 좌표점이 필요합니다.
     * 
     * @return 좌표 리스트 (여러 ElementCoordinate 객체 포함)
     * @throws CloudSDKException 좌표가 2개 미만인 경우
     */
    @Override
    public List<ElementCoordinate> convertToList() {
        if (this.coordinates.length < 2) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        List<ElementCoordinate> coordinateList = new ArrayList<>();
        for (Double[] coordinate : this.coordinates) {
            coordinateList.add(new ElementCoordinate()
                    .setLongitude(coordinate[0])
                    .setLatitude(coordinate[1]));
        }
        return coordinateList;
    }

    /**
     * ElementCoordinate 객체 리스트를 좌표 배열로 변환합니다.
     * 
     * 이 메서드는 ElementCoordinate 객체 리스트를 선 지오메트리에 맞는 좌표 배열로 변환합니다.
     * 선을 그리려면 최소 2개 이상의 좌표점이 필요합니다.
     * 
     * @param coordinateList 변환할 좌표 리스트
     * @throws CloudSDKException 좌표 리스트가 비어있거나 2개 미만인 경우
     */
    @Override
    public void adapterCoordinateType(List<ElementCoordinate> coordinateList) {
        if (CollectionUtils.isEmpty(coordinateList) || coordinateList.size() < 2) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        this.coordinates = new Double[coordinateList.size()][2];
        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i][0] = coordinateList.get(i).getLongitude();
            this.coordinates[i][1] = coordinateList.get(i).getLatitude();
        }
    }

    @Override
    public String toString() {
        return "ElementLineStringGeometry{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public Double[][] getCoordinates() {
        return coordinates;
    }

    public ElementLineStringGeometry setCoordinates(Double[][] coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }
}
