package com.dji.sdk.common;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 페이지네이션 데이터 클래스
 * 
 * 페이지네이션이 필요한 데이터 응답 형식을 정의합니다.
 * 데이터 목록과 페이지네이션 정보를 함께 포함합니다.
 * 
 * 주요 구성 요소:
 * - list: 데이터 목록을 저장하는 컬렉션
 * - pagination: 페이지네이션 정보 (페이지 번호, 크기, 총 개수 등)
 * 
 * 이 클래스는 API 응답에서 페이지네이션된
 * 데이터를 표준화된 형식으로 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Schema(description = "Format of paged data")
public class PaginationData<T> {

    /**
     * 데이터 목록을 저장하는 컬렉션
     * 
     * 페이지네이션된 데이터의 실제 목록을 포함합니다.
     */
    @Schema(description = "The collection in which the data list is stored.")
    private List<T> list;

    /**
     * 페이지네이션 정보
     * 
     * 페이지네이션 표시에 사용됩니다. 이 필드 이름은 변경할 수 없습니다.
     * 파일럿과 동일해야 하기 때문입니다.
     */
    @Schema(description = "Used for paging display. These field names cannot be changed. Because they need to be the same as the pilot.")
    private Pagination pagination;

    /**
     * 기본 생성자
     * 
     * 빈 PaginationData 인스턴스를 생성합니다.
     */
    public PaginationData() {
    }

    /**
     * 매개변수가 있는 생성자
     * 
     * 데이터 목록과 페이지네이션 정보를 설정하여
     * PaginationData 인스턴스를 생성합니다.
     * 
     * @param list 데이터 목록
     * @param pagination 페이지네이션 정보
     */
    public PaginationData(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "PaginationData{" +
                "list=" + list +
                ", pagination=" + pagination +
                '}';
    }

    /**
     * 데이터 목록을 반환합니다.
     * 
     * @return 데이터 목록
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 데이터 목록을 설정합니다.
     * 
     * @param list 설정할 데이터 목록
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public PaginationData<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * 페이지네이션 정보를 반환합니다.
     * 
     * @return 페이지네이션 정보
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 페이지네이션 정보를 설정합니다.
     * 
     * @param pagination 설정할 페이지네이션 정보
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public PaginationData<T> setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }
}
