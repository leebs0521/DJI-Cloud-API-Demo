package com.dji.sdk.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 페이지네이션 클래스
 * 
 * 페이지네이션 표시에 사용됩니다. 이 필드 이름들은 변경할 수 없습니다.
 * 파일럿과 동일해야 하기 때문입니다.
 * 
 * 주요 구성 요소:
 * - page: 현재 페이지 번호
 * - pageSize: 페이지당 표시되는 데이터 양
 * - total: 전체 데이터의 총 개수
 * 
 * 이 클래스는 API 응답에서 페이지네이션 정보를
 * 표준화된 형식으로 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Schema(description = "Used for paging display")
public class Pagination {

    /**
     * 현재 페이지 번호
     * 
     * 현재 표시되고 있는 페이지의 번호입니다.
     */
    @Schema(description = "The current page number.", example = "1")
    private long page;

    /**
     * 페이지당 표시되는 데이터 양
     * 
     * 한 페이지에 표시되는 데이터의 개수입니다.
     */
    @Schema(description = "The amount of data displayed per page.", example = "10")
    @JsonProperty("page_size")
    private long pageSize;

    /**
     * 전체 데이터의 총 개수
     * 
     * 전체 데이터의 총 개수입니다.
     */
    @Schema(description = "The total amount of all data.", example = "10")
    private long total;

    /**
     * 기본 생성자
     * 
     * 빈 Pagination 인스턴스를 생성합니다.
     */
    public Pagination() {
    }

    /**
     * 매개변수가 있는 생성자
     * 
     * 페이지 번호, 페이지 크기, 총 개수를 설정하여
     * Pagination 인스턴스를 생성합니다.
     * 
     * @param page 현재 페이지 번호
     * @param pageSize 페이지당 표시되는 데이터 양
     * @param total 전체 데이터의 총 개수
     */
    public Pagination(long page, long pageSize, long total) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }

    /**
     * 현재 페이지 번호를 반환합니다.
     * 
     * @return 현재 페이지 번호
     */
    public long getPage() {
        return page;
    }

    /**
     * 현재 페이지 번호를 설정합니다.
     * 
     * @param page 설정할 페이지 번호
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public Pagination setPage(long page) {
        this.page = page;
        return this;
    }

    /**
     * 페이지당 표시되는 데이터 양을 반환합니다.
     * 
     * @return 페이지당 표시되는 데이터 양
     */
    public long getPageSize() {
        return pageSize;
    }

    /**
     * 페이지당 표시되는 데이터 양을 설정합니다.
     * 
     * @param pageSize 설정할 페이지 크기
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public Pagination setPageSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 전체 데이터의 총 개수를 반환합니다.
     * 
     * @return 전체 데이터의 총 개수
     */
    public long getTotal() {
        return total;
    }

    /**
     * 전체 데이터의 총 개수를 설정합니다.
     * 
     * @param total 설정할 총 개수
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public Pagination setTotal(long total) {
        this.total = total;
        return this;
    }
}
