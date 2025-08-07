package com.dji.sdk.cloudapi.control;

/**
 * OSD 정보 푸시 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OSD(On-Screen Display) 정보를 푸시하기 위한 클래스입니다.
 * 드론의 자세, 위치, 속도, 짐벌 각도 등의 실시간 정보를 포함하여 OSD 정보를 전송합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class OsdInfoPush {

    /**
     * 드론 자세 헤딩
     * 드론의 헤딩 각도 (도)
     */
    private Float attitudeHead;

    /**
     * 위도
     * 드론의 현재 위도
     */
    private Float latitude;

    /**
     * 경도
     * 드론의 현재 경도
     */
    private Float longitude;

    /**
     * 고도
     * 드론의 현재 고도 (미터)
     */
    private Float height;

    /**
     * X축 속도
     * 드론의 X축 방향 속도 (m/s)
     */
    private Float speedX;

    /**
     * Y축 속도
     * 드론의 Y축 방향 속도 (m/s)
     */
    private Float speedY;

    /**
     * Z축 속도
     * 드론의 Z축 방향 속도 (m/s)
     */
    private Float speedZ;

    /**
     * 짐벌 피치 각도
     * 짐벌의 피치 각도 (도)
     */
    private Float gimbalPitch;

    /**
     * 짐벌 롤 각도
     * 짐벌의 롤 각도 (도)
     */
    private Float gimbalRoll;

    /**
     * 짐벌 요 각도
     * 짐벌의 요 각도 (도)
     */
    private Float gimbalYaw;

    /**
     * 기본 생성자
     */
    public OsdInfoPush() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OsdInfoPush{" +
                "attitudeHead=" + attitudeHead +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", speedZ=" + speedZ +
                ", gimbalPitch=" + gimbalPitch +
                ", gimbalRoll=" + gimbalRoll +
                ", gimbalYaw=" + gimbalYaw +
                '}';
    }

    /**
     * 드론 자세 헤딩을 반환합니다.
     * 
     * @return 드론 자세 헤딩 (도)
     */
    public Float getAttitudeHead() {
        return attitudeHead;
    }

    /**
     * 드론 자세 헤딩을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param attitudeHead 설정할 드론 자세 헤딩 (도)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setAttitudeHead(Float attitudeHead) {
        this.attitudeHead = attitudeHead;
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
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setLatitude(Float latitude) {
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
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 고도를 반환합니다.
     * 
     * @return 고도 (미터)
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 고도 (미터)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setHeight(Float height) {
        this.height = height;
        return this;
    }

    /**
     * X축 속도를 반환합니다.
     * 
     * @return X축 속도 (m/s)
     */
    public Float getSpeedX() {
        return speedX;
    }

    /**
     * X축 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param speedX 설정할 X축 속도 (m/s)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setSpeedX(Float speedX) {
        this.speedX = speedX;
        return this;
    }

    /**
     * Y축 속도를 반환합니다.
     * 
     * @return Y축 속도 (m/s)
     */
    public Float getSpeedY() {
        return speedY;
    }

    /**
     * Y축 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param speedY 설정할 Y축 속도 (m/s)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setSpeedY(Float speedY) {
        this.speedY = speedY;
        return this;
    }

    /**
     * Z축 속도를 반환합니다.
     * 
     * @return Z축 속도 (m/s)
     */
    public Float getSpeedZ() {
        return speedZ;
    }

    /**
     * Z축 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param speedZ 설정할 Z축 속도 (m/s)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setSpeedZ(Float speedZ) {
        this.speedZ = speedZ;
        return this;
    }

    /**
     * 짐벌 피치 각도를 반환합니다.
     * 
     * @return 짐벌 피치 각도 (도)
     */
    public Float getGimbalPitch() {
        return gimbalPitch;
    }

    /**
     * 짐벌 피치 각도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param gimbalPitch 설정할 짐벌 피치 각도 (도)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setGimbalPitch(Float gimbalPitch) {
        this.gimbalPitch = gimbalPitch;
        return this;
    }

    /**
     * 짐벌 롤 각도를 반환합니다.
     * 
     * @return 짐벌 롤 각도 (도)
     */
    public Float getGimbalRoll() {
        return gimbalRoll;
    }

    /**
     * 짐벌 롤 각도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param gimbalRoll 설정할 짐벌 롤 각도 (도)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setGimbalRoll(Float gimbalRoll) {
        this.gimbalRoll = gimbalRoll;
        return this;
    }

    /**
     * 짐벌 요 각도를 반환합니다.
     * 
     * @return 짐벌 요 각도 (도)
     */
    public Float getGimbalYaw() {
        return gimbalYaw;
    }

    /**
     * 짐벌 요 각도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param gimbalYaw 설정할 짐벌 요 각도 (도)
     * @return 현재 OsdInfoPush 객체
     */
    public OsdInfoPush setGimbalYaw(Float gimbalYaw) {
        this.gimbalYaw = gimbalYaw;
        return this;
    }
}
