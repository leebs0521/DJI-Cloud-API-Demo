package com.dji.sample.control.model.param;

import com.dji.sdk.cloudapi.control.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 특정 지점으로 비행하는 파라미터 클래스
 * 
 * 드론이 특정 지점으로 비행할 때 사용하는 파라미터를 정의합니다.
 * 최대 속도, 비행 지점 목록 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlyToPointParam {

    /** 비행 작업 ID */
    private String flyToId;

    /** 최대 속도 (1-15 m/s) */
    @Range(min = 1, max = 15)
    @NotNull
    private Integer maxSpeed;

    /**
     * 비행 지점 목록
     * M30 시리즈는 하나의 지점만 지원합니다.
     */
    @Size(min = 1)
    @NotNull
    private List<@Valid Point> points;
}
