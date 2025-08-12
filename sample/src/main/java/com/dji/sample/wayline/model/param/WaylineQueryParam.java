package com.dji.sample.wayline.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 파일 조회 파라미터 클래스
 * 
 * 이 클래스는 웨이라인 파일 목록을 조회할 때 사용되는 검색 및 페이징 파라미터들을 정의합니다.
 * 웨이라인 파일의 필터링, 정렬, 페이징 기능을 지원하며,
 * Lombok 어노테이션을 통해 빌더 패턴과 기본값 설정을 제공합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일 목록 조회 파라미터 정의
 * - 즐겨찾기 필터링 지원
 * - 페이징 처리 (페이지 번호, 페이지 크기)
 * - 정렬 옵션 지원
 * - 템플릿 타입별 필터링 지원
 * 
 * 사용 시나리오:
 * - 웨이라인 파일 목록 조회 API 호출 시
 * - DJI Pilot에서 웨이라인 파일 브라우징 시
 * - 웨이라인 파일 관리 인터페이스에서 검색 시
 * 
 * Lombok 어노테이션:
 * - @Data: getter, setter, toString, equals, hashCode 자동 생성
 * - @Builder: 빌더 패턴 지원
 * - @AllArgsConstructor: 모든 필드를 인자로 받는 생성자 생성
 * - @NoArgsConstructor: 기본 생성자 생성
 * - @Builder.Default: 빌더 패턴에서 기본값 설정
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineQueryParam {

    /**
     * 즐겨찾기 여부
     * 즐겨찾기로 설정된 웨이라인 파일만 조회할지 여부
     * 
     * 데이터 타입: boolean
     * 
     * 값 의미:
     * - true: 즐겨찾기로 설정된 웨이라인 파일만 조회
     * - false: 모든 웨이라인 파일 조회 (즐겨찾기 여부 무관)
     * 
     * 사용 목적:
     * - 사용자가 자주 사용하는 웨이라인 파일만 빠르게 조회
     * - 즐겨찾기 기능을 통한 웨이라인 파일 관리
     * - 개인화된 웨이라인 파일 목록 제공
     */
    private boolean favorited;

    /**
     * 페이지 번호
     * 조회할 페이지의 번호 (1부터 시작)
     * 
     * 데이터 타입: int
     * 기본값: 1 (@Builder.Default)
     * 
     * 페이징 규칙:
     * - 1부터 시작하는 양의 정수
     * - 1: 첫 번째 페이지
     * - 2: 두 번째 페이지
     * - ...
     * 
     * 사용 목적:
     * - 대량의 웨이라인 파일을 페이지 단위로 분할 조회
     * - UI에서 페이지네이션 구현
     * - 서버 부하 분산
     */
    @Builder.Default
    private int page = 1;

    /**
     * 페이지 크기
     * 한 페이지에 조회할 웨이라인 파일의 개수
     * 
     * 데이터 타입: int
     * 기본값: 10 (@Builder.Default)
     * 
     * 권장 범위:
     * - 최소값: 1
     * - 최대값: 100 (서버 성능 고려)
     * - 기본값: 10 (UI 표시에 적합한 크기)
     * 
     * 사용 목적:
     * - 한 번에 조회할 데이터 양 제어
     * - 네트워크 트래픽 최적화
     * - UI 렌더링 성능 향상
     */
    @Builder.Default
    private int pageSize = 10;

    /**
     * 정렬 기준
     * 웨이라인 파일 목록을 정렬할 기준 필드명
     * 
     * 데이터 타입: String
     * 
     * 일반적인 정렬 기준:
     * - "create_time": 생성 시간순 정렬
     * - "update_time": 수정 시간순 정렬
     * - "name": 이름순 정렬
     * - "size": 파일 크기순 정렬
     * 
     * 정렬 방향:
     * - 기본값: 내림차순 (최신순)
     * - 필요시 "asc" 또는 "desc" 접미사 추가 가능
     * 
     * 사용 목적:
     * - 사용자가 원하는 순서로 웨이라인 파일 정렬
     * - 최신 파일 우선 표시
     * - 알파벳순 또는 크기순 정렬
     */
    private String orderBy;

    /**
     * 템플릿 타입 배열
     * 특정 템플릿 타입의 웨이라인 파일만 필터링하여 조회
     * 
     * 데이터 타입: Integer[]
     * 
     * 템플릿 타입 값:
     * - 0: 웨이포인트 (Waypoint)
     * - 1: 2D 매핑 (2D Mapping)
     * - 2: 3D 매핑 (3D Mapping)
     * - 4: 스트립 매핑 (Strip Mapping)
     * 
     * 필터링 규칙:
     * - null 또는 빈 배열: 모든 템플릿 타입 조회
     * - 특정 값들만 포함된 배열: 해당 템플릿 타입만 조회
     * 
     * 사용 예시:
     * - [1, 2]: 2D 매핑과 3D 매핑 웨이라인만 조회
     * - [0]: 웨이포인트 웨이라인만 조회
     * - null: 모든 템플릿 타입 조회
     * 
     * 사용 목적:
     * - 용도별 웨이라인 파일 분류 조회
     * - 특정 작업 유형에 맞는 웨이라인 파일만 빠르게 찾기
     * - 웨이라인 파일 관리 효율성 향상
     */
    private Integer[] templateType;
}
