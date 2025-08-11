package com.dji.sample.manage.model.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * HMS(Health Management System) JSON 유틸리티 클래스
 * 
 * DJI Cloud API의 HMS 메시지 정보를 JSON 파일에서 로드하고 관리하는 유틸리티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS JSON 파일 관리
 *    - hms.json 파일을 클래스패스에서 로드
 *    - JSON 파일 파싱 및 메모리 캐싱
 *    - JSON 노드 구조 관리
 * 
 * 2. HMS 메시지 조회 기능
 *    - 키 기반 HMS 메시지 조회
 *    - 다국어 지원 (중국어, 영어)
 *    - 메시지가 없을 경우 기본 객체 반환
 * 
 * 3. Spring 통합 기능
 *    - @PostConstruct를 통한 애플리케이션 시작 시 자동 로드
 *    - Spring의 ObjectMapper 의존성 주입
 *    - 컴포넌트 스캔을 통한 자동 등록
 * 
 * 4. 에러 처리 및 로깅
 *    - JSON 파일 로드 실패 시 에러 로깅
 *    - 예외 상황에 대한 안전한 처리
 *    - SLF4J를 통한 구조화된 로깅
 * 
 * 이 클래스는 hms.json 파일의 구조를 기반으로 HMS 메시지를
 * 키-값 형태로 관리하며, 디바이스의 건강 상태 메시지를
 * 다국어로 제공하는 기능을 담당합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
@Slf4j
@Component
public class HmsJsonUtil {

    /**
     * JSON 파싱을 위한 ObjectMapper 인스턴스
     * Spring에서 주입받아 사용
     */
    private static ObjectMapper mapper;

    /**
     * Spring의 의존성 주입을 통해 ObjectMapper를 설정합니다.
     * 
     * @param mapper 주입받을 ObjectMapper 인스턴스
     */
    @Autowired
    public void setMapper(ObjectMapper mapper) {
        HmsJsonUtil.mapper = mapper;
    }

    /**
     * hms.json 파일에서 로드된 JSON 노드 구조
     * 애플리케이션 시작 시 초기화되어 메모리에 캐싱됨
     */
    private static JsonNode nodes;

    /**
     * 기본 생성자
     * 유틸리티 클래스이므로 외부에서 인스턴스 생성 방지
     */
    private HmsJsonUtil(){

    }

    /**
     * 애플리케이션 시작 시 hms.json 파일을 로드합니다.
     * 
     * 이 메서드는 다음과 같은 작업을 수행합니다:
     * - 클래스패스에서 hms.json 파일을 찾아 로드
     * - JSON 파일을 파싱하여 JsonNode 구조로 변환
     * - 파싱된 데이터를 정적 필드에 캐싱
     * - 로드 실패 시 에러 로깅 및 예외 처리
     */
    @PostConstruct
    private void loadJsonFile() {
        try (InputStream inputStream = new ClassPathResource("hms.json").getInputStream()){
            nodes = mapper.readTree(inputStream);
        } catch (IOException e) {
            log.error("hms.json failed to load.");
            e.printStackTrace();
        }
    }

    /**
     * 지정된 키에 해당하는 HMS 메시지를 조회합니다.
     * 
     * 이 메서드는 다음과 같은 기능을 제공합니다:
     * - 키에 해당하는 HMS 메시지 조회
     * - 메시지가 없을 경우 빈 HmsMessage 객체 반환
     * - JSON 노드를 HmsMessage 객체로 변환
     * 
     * @param key 조회할 HMS 메시지의 키
     * @return 해당 키의 HMS 메시지 (없을 경우 빈 객체)
     */
    public static HmsMessage get(String key) {
        if (nodes.get(key) == null) {
            return new HmsMessage();
        }
        return mapper.convertValue(nodes.get(key), HmsMessage.class);
    }
}
