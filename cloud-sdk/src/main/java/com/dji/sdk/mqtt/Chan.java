package com.dji.sdk.mqtt;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * 채널 클래스
 * 
 * 이 클래스는 MQTT 통신에서 요청과 응답을 동기화하기 위한
 * 채널 기능을 제공합니다.
 * 
 * 주의사항:
 * 이 데모는 기능적 완결성을 위한 것으로, 실제 사용에는 권장되지 않습니다.
 * 
 * 주요 기능:
 * - 요청 ID 기반 채널 생성
 * - 타임아웃 기반 응답 대기
 * - 스레드 동기화
 * 
 * 이 클래스는 ConcurrentHashMap을 사용하여
 * 스레드 안전한 채널 관리를 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/22
 * @version 0.1
 */
public class Chan {

    /**
     * 채널 저장소
     * 
     * 요청 ID를 키로 하는 채널들을 저장합니다.
     */
    private static final ConcurrentHashMap<String, Chan> CHANNEL = new ConcurrentHashMap<>();

    /**
     * 시간 단위 (나노초)
     * 
     * 타임아웃 계산에 사용되는 단위입니다.
     */
    private static final int UNIT = 1000_000;

    /**
     * 응답 데이터
     * 
     * 채널에 저장될 응답 데이터입니다.
     */
    private volatile CommonTopicResponse data;

    /**
     * 대기 스레드
     * 
     * 응답을 기다리는 스레드입니다.
     */
    private volatile Thread t;

    /**
     * 기본 생성자
     * 
     * 빈 Chan 인스턴스를 생성합니다.
     */
    private Chan () {

    }

    /**
     * 채널 인스턴스를 가져옵니다.
     * 
     * @param tid 요청 ID
     * @param isNeedCreate 새로 생성할지 여부
     * @return 채널 인스턴스
     */
    public static Chan getInstance(String tid, boolean isNeedCreate) {
        if (!isNeedCreate) {
            return CHANNEL.get(tid);
        }
        Chan chan = new Chan();
        CHANNEL.put(tid, chan);
        return chan;
    }

    /**
     * 응답을 가져옵니다.
     * 
     * 지정된 타임아웃 동안 응답을 기다립니다.
     * 
     * @param tid 요청 ID
     * @param timeout 타임아웃 (밀리초)
     * @return 응답 데이터
     */
    public CommonTopicResponse get(String tid, long timeout) {
        Chan chan = CHANNEL.get(tid);
        if (Objects.isNull(chan)) {
            return null;
        }
        chan.t = Thread.currentThread();
        LockSupport.parkNanos(chan.t, timeout * UNIT);
        chan.t = null;
        CHANNEL.remove(tid);
        return chan.data;
    }

    /**
     * 응답을 저장합니다.
     * 
     * @param response 저장할 응답
     */
    public void put(CommonTopicResponse response) {
        Chan chan = CHANNEL.get(response.getTid());
        if (Objects.isNull(chan)) {
            return;
        }
        chan.data = response;
        if (chan.t == null) {
            return;
        }
        LockSupport.unpark(chan.t);
    }
}