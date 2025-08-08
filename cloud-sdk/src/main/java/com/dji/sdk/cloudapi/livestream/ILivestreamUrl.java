package com.dji.sdk.cloudapi.livestream;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 라이브스트림 URL을 나타내는 인터페이스
 * 다양한 스트리밍 프로토콜의 URL을 통일된 방식으로 처리할 수 있도록 합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public interface ILivestreamUrl {

    /**
     * 라이브스트림 URL을 문자열로 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 라이브스트림 URL 문자열
     */
    @JsonValue
    String toString();

    /**
     * 라이브스트림 URL 객체의 복사본을 생성합니다.
     * 
     * @return 복사된 라이브스트림 URL 객체
     */
    ILivestreamUrl clone();
}
