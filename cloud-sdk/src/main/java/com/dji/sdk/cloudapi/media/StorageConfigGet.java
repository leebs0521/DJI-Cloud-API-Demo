package com.dji.sdk.cloudapi.media;

/**
 * 스토리지 설정 조회 데이터 클래스
 * 
 * 이 클래스는 스토리지 설정을 조회하기 위한 요청 데이터를 정의합니다.
 * 특정 모듈의 스토리지 설정 정보를 가져오는 데 사용됩니다.
 * 
 * 주요 구성 요소:
 * - module: 스토리지 설정 조회 모듈 열거형
 * 
 * 이 클래스는 클라우드 스토리지의 설정 정보를 조회하는 API 호출에 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public class StorageConfigGet {

    /**
     * 스토리지 설정 조회 모듈
     * 
     * 조회할 스토리지 설정의 모듈을 지정합니다.
     * 미디어, 로그, 기타 파일 타입별로 다른 설정을 가져올 수 있습니다.
     */
    private StorageConfigGetModuleEnum module;

    public StorageConfigGet() {
    }

    @Override
    public String toString() {
        return "StorageConfigGet{" +
                "module=" + module +
                '}';
    }

    /**
     * 스토리지 설정 조회 모듈을 반환합니다.
     * 
     * @return 스토리지 설정 조회 모듈 열거형
     */
    public StorageConfigGetModuleEnum getModule() {
        return module;
    }

    /**
     * 스토리지 설정 조회 모듈을 설정합니다.
     * 
     * @param module 스토리지 설정 조회 모듈 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StorageConfigGet setModule(StorageConfigGetModuleEnum module) {
        this.module = module;
        return this;
    }
}
