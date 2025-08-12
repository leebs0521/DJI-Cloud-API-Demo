package com.dji.sample.wayline.model.dto;

/**
 * DJI Cloud API KMZ 파일 속성 상수 클래스
 * 
 * 이 클래스는 KMZ(Keyhole Markup Language Zipped) 웨이라인 파일의 구조와 속성을 정의하는 상수들을 제공합니다.
 * KMZ 파일은 Google Earth에서 생성된 웨이라인 파일로, 드론의 자동 비행 경로를 정의하는 압축된 XML 파일입니다.
 * 
 * 주요 기능:
 * - KMZ 파일의 디렉토리 구조 상수 정의
 * - KMZ 파일 내 XML 태그 상수 정의
 * - 웨이라인 파일 확장자 및 파일명 상수 정의
 * - 드론 및 페이로드 정보 태그 상수 정의
 * 
 * KMZ 파일 구조:
 * - wpmz/                    # 루트 디렉토리
 *   ├── res/                 # 리소스 디렉토리
 *   ├── template.kml         # 템플릿 KML 파일
 *   └── waylines.wpml        # 웨이라인 WPML 파일
 * 
 * 사용 시나리오:
 * - KMZ 파일 파싱 및 검증 시 사용
 * - 웨이라인 파일 업로드 처리 시 사용
 * - 웨이라인 파일 구조 검사 시 사용
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class KmzFileProperties {

    /**
     * 기본 생성자를 private으로 설정하여 인스턴스 생성을 방지
     * 이 클래스는 상수만을 제공하는 유틸리티 클래스
     */
    private KmzFileProperties() {

    }

    /**
     * 웨이라인 파일 확장자
     * DJI 웨이라인 파일의 표준 확장자 (.kmz)
     */
    public static final String WAYLINE_FILE_SUFFIX = ".kmz";

    /**
     * KMZ 파일 루트 디렉토리명
     * KMZ 파일 내부의 최상위 디렉토리명
     */
    public static final String FILE_DIR_FIRST = "wpmz";

    /**
     * KMZ 파일 리소스 디렉토리명
     * KMZ 파일 내부의 리소스 파일들이 저장되는 디렉토리명
     */
    public static final String FILE_DIR_SECOND_RES = "res";

    /**
     * KMZ 파일 템플릿 KML 파일명
     * KMZ 파일 내부의 템플릿 정보를 담고 있는 KML 파일명
     * 드론 모델, 페이로드 정보, 템플릿 타입 등의 메타데이터 포함
     */
    public static final String FILE_DIR_SECOND_TEMPLATE = "template.kml";

    /**
     * KMZ 파일 웨이라인 WPML 파일명
     * KMZ 파일 내부의 실제 웨이라인 경로 정보를 담고 있는 WPML 파일명
     * 웨이포인트, 비행 경로, 고도 등의 상세 비행 정보 포함
     */
    public static final String FILE_DIR_SECOND_WAYLINES = "waylines.wpml";

    /**
     * WPML 태그 접두사
     * WPML 파일 내 XML 태그들의 네임스페이스 접두사
     */
    public static final String TAG_WPML_PREFIX = "wpml:";

    /**
     * 드론 정보 태그명
     * 템플릿 KML 파일에서 드론 모델 정보를 담고 있는 XML 태그명
     */
    public static final String TAG_DRONE_INFO = "droneInfo";

    /**
     * 드론 열거값 태그명
     * 템플릿 KML 파일에서 드론 모델의 열거값을 담고 있는 XML 태그명
     */
    public static final String TAG_DRONE_ENUM_VALUE = "droneEnumValue";

    /**
     * 드론 서브 열거값 태그명
     * 템플릿 KML 파일에서 드론 모델의 서브 열거값을 담고 있는 XML 태그명
     */
    public static final String TAG_DRONE_SUB_ENUM_VALUE = "droneSubEnumValue";

    /**
     * 페이로드 정보 태그명
     * 템플릿 KML 파일에서 페이로드 장비 정보를 담고 있는 XML 태그명
     */
    public static final String TAG_PAYLOAD_INFO = "payloadInfo";

    /**
     * 페이로드 열거값 태그명
     * 템플릿 KML 파일에서 페이로드 장비의 열거값을 담고 있는 XML 태그명
     */
    public static final String TAG_PAYLOAD_ENUM_VALUE = "payloadEnumValue";

    /**
     * 페이로드 서브 열거값 태그명
     * 템플릿 KML 파일에서 페이로드 장비의 서브 열거값을 담고 있는 XML 태그명
     */
    public static final String TAG_PAYLOAD_SUB_ENUM_VALUE = "payloadSubEnumValue";

    /**
     * 템플릿 타입 태그명
     * 템플릿 KML 파일에서 웨이라인의 템플릿 타입을 담고 있는 XML 태그명
     * 정찰, 측량, 모니터링 등의 웨이라인 유형을 나타냄
     */
    public static final String TAG_TEMPLATE_TYPE = "templateType";
}
