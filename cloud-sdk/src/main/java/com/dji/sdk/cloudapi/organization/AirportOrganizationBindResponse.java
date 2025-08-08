package com.dji.sdk.cloudapi.organization;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 공항 조직 바인딩 응답 클래스
 * 
 * 이 클래스는 공항(도크)의 디바이스 조직 바인딩 작업 결과를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 바인딩 작업 중 발생한
 * 오류 정보들을 포함합니다.
 * 
 * 주요 구성 요소:
 * - errInfos: 조직 바인딩 정보 목록 (1-2개, 오류 정보 포함)
 * 
 * 이 클래스는 공항에 연결된 디바이스들의 조직 바인딩 작업 결과를
 * 응답으로 반환하는 데 사용됩니다. 바인딩 성공/실패 여부와
 * 관련 오류 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AirportOrganizationBindResponse extends BaseModel {

    /**
     * 조직 바인딩 정보 목록
     * 
     * 공항에 연결된 디바이스들의 조직 바인딩 작업 결과를 담은 목록입니다.
     * 최소 1개, 최대 2개의 디바이스 바인딩 정보를 포함할 수 있습니다.
     * 각 디바이스의 시리얼 번호와 바인딩 결과 코드(성공/실패)를 포함합니다.
     * 
     * 바인딩 성공 시: errCode = 0
     * 바인딩 실패 시: errCode = 에러 코드
     */
    @NotNull
    @Size(min = 1, max = 2)
    private List<@Valid OrganizationBindInfo> errInfos;

    public AirportOrganizationBindResponse() {
    }

    @Override
    public String toString() {
        return "AirportOrganizationBindResponse{" +
                "errInfos=" + errInfos +
                '}';
    }

    /**
     * 조직 바인딩 정보 목록을 반환합니다.
     * 
     * @return 조직 바인딩 정보 목록
     */
    public List<OrganizationBindInfo> getErrInfos() {
        return errInfos;
    }

    /**
     * 조직 바인딩 정보 목록을 설정합니다.
     * 
     * @param errInfos 조직 바인딩 정보 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public AirportOrganizationBindResponse setErrInfos(List<OrganizationBindInfo> errInfos) {
        this.errInfos = errInfos;
        return this;
    }
}
