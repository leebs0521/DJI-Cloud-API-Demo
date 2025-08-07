package com.dji.sdk.cloudapi.airsense;

/**
 * 항공기 감지 경고 클래스
 * 
 * 이 클래스는 DJI AirSense 시스템에서 감지된 주변 항공기의 정보를 담습니다.
 * 항공기의 위치, 고도, 방향, 거리, 위험도 등 상세한 정보를 포함합니다.
 * 이를 통해 드론 조종사가 주변 항공기와의 충돌을 방지할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public class AirsenseWarning {

    /**
     * ICAO 민간 항공기 주소
     * 항공기의 고유 식별 번호
     */
    private String icao;

    /**
     * 위험도 레벨
     * 숫자가 높을수록 더 위험합니다.
     * 3 이상인 경우 항공기가 회피 조치를 취하는 것이 권장됩니다.
     */
    private WarningLevelEnum warningLevel;

    /**
     * 항공기 위치의 위도 (각도 값)
     * 남위는 음수, 북위는 양수
     * 소수점 6자리까지 정확
     */
    private Float latitude;

    /**
     * 항공기 위치의 경도 (각도 값)
     * 서경은 음수, 동경은 양수
     * 소수점 6자리까지 정확
     */
    private Float longitude;

    /**
     * 항공기의 절대 고도
     * 단위: 미터
     */
    private Integer altitude;

    /**
     * 절대 고도 타입
     * 타원체 고도 또는 해수면 기준 고도
     */
    private AltitudeTypeEnum altitudeType;

    /**
     * 항공기의 방향각 (각도 값)
     * 0도는 북쪽, 90도는 동쪽
     * 소수점 1자리까지 정확
     */
    private Float heading;

    /**
     * 드론 대비 항공기의 상대 고도
     * 단위: 미터
     */
    private Integer relativeAltitude;

    /**
     * 상대 고도 변화 추세
     * 증가, 감소, 변화 없음
     */
    private VertTrendEnum vertTrend;

    /**
     * 드론으로부터의 수평 거리
     * 단위: 미터
     */
    private Integer distance;

    /**
     * 기본 생성자
     */
    public AirsenseWarning() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "AirsenseWarning{" +
                "icao='" + icao + '\'' +
                ", warningLevel=" + warningLevel +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", altitudeType=" + altitudeType +
                ", heading=" + heading +
                ", relativeAltitude=" + relativeAltitude +
                ", vertTrend=" + vertTrend +
                ", distance=" + distance +
                '}';
    }

    /**
     * ICAO 주소를 반환합니다.
     * 
     * @return ICAO 주소
     */
    public String getIcao() {
        return icao;
    }

    /**
     * ICAO 주소를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param icao 설정할 ICAO 주소
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setIcao(String icao) {
        this.icao = icao;
        return this;
    }

    /**
     * 위험도 레벨을 반환합니다.
     * 
     * @return 위험도 레벨
     */
    public WarningLevelEnum getWarningLevel() {
        return warningLevel;
    }

    /**
     * 위험도 레벨을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param warningLevel 설정할 위험도 레벨
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setWarningLevel(WarningLevelEnum warningLevel) {
        this.warningLevel = warningLevel;
        return this;
    }

    /**
     * 위도를 반환합니다.
     * 
     * @return 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 위도
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 경도를 반환합니다.
     * 
     * @return 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 경도
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 절대 고도를 반환합니다.
     * 
     * @return 절대 고도 (미터)
     */
    public Integer getAltitude() {
        return altitude;
    }

    /**
     * 절대 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param altitude 설정할 절대 고도 (미터)
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setAltitude(Integer altitude) {
        this.altitude = altitude;
        return this;
    }

    /**
     * 고도 타입을 반환합니다.
     * 
     * @return 고도 타입
     */
    public AltitudeTypeEnum getAltitudeType() {
        return altitudeType;
    }

    /**
     * 고도 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param altitudeType 설정할 고도 타입
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setAltitudeType(AltitudeTypeEnum altitudeType) {
        this.altitudeType = altitudeType;
        return this;
    }

    /**
     * 방향각을 반환합니다.
     * 
     * @return 방향각 (도)
     */
    public Float getHeading() {
        return heading;
    }

    /**
     * 방향각을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param heading 설정할 방향각 (도)
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setHeading(Float heading) {
        this.heading = heading;
        return this;
    }

    /**
     * 상대 고도를 반환합니다.
     * 
     * @return 상대 고도 (미터)
     */
    public Integer getRelativeAltitude() {
        return relativeAltitude;
    }

    /**
     * 상대 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param relativeAltitude 설정할 상대 고도 (미터)
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setRelativeAltitude(Integer relativeAltitude) {
        this.relativeAltitude = relativeAltitude;
        return this;
    }

    /**
     * 수직 변화 추세를 반환합니다.
     * 
     * @return 수직 변화 추세
     */
    public VertTrendEnum getVertTrend() {
        return vertTrend;
    }

    /**
     * 수직 변화 추세를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param vertTrend 설정할 수직 변화 추세
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setVertTrend(VertTrendEnum vertTrend) {
        this.vertTrend = vertTrend;
        return this;
    }

    /**
     * 수평 거리를 반환합니다.
     * 
     * @return 수평 거리 (미터)
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * 수평 거리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param distance 설정할 수평 거리 (미터)
     * @return 현재 AirsenseWarning 객체
     */
    public AirsenseWarning setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }
}
