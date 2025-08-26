package com.dji.sdk.cloudapi.device;

/**
 * 스토리지 정보 클래스
 * 
 * 이 클래스는 디바이스의 스토리지 정보를 담습니다.
 * 전체 용량과 사용된 용량을 포함하여 스토리지 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/11
 */
public class Storage {

    /**
     * 전체 스토리지 용량 (KB)
     */
    private Long total;

    /**
     * 사용된 스토리지 용량 (KB)
     */
    private Long used;

    /**
     * 기본 생성자
     */
    public Storage() {
    }

    @Override
    public String toString() {
        return "Storage{" +
                "total=" + total +
                ", used=" + used +
                '}';
    }

    /**
     * 전체 스토리지 용량을 반환합니다.
     * 
     * @return 전체 스토리지 용량 (바이트)
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 전체 스토리지 용량을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param total 설정할 전체 스토리지 용량 (바이트)
     * @return 현재 Storage 객체
     */
    public Storage setTotal(Long total) {
        this.total = total;
        return this;
    }

    /**
     * 사용된 스토리지 용량을 반환합니다.
     * 
     * @return 사용된 스토리지 용량 (바이트)
     */
    public Long getUsed() {
        return used;
    }

    /**
     * 사용된 스토리지 용량을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param used 설정할 사용된 스토리지 용량 (바이트)
     * @return 현재 Storage 객체
     */
    public Storage setUsed(Long used) {
        this.used = used;
        return this;
    }
}
