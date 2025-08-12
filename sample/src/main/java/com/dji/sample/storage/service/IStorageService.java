package com.dji.sample.storage.service;

import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;

/**
 * DJI Cloud API 스토리지 서비스 인터페이스
 * 
 * 이 인터페이스는 DJI Pilot에서 미디어 파일과 웨이라인을 업로드하기 위한
 * 임시 자격 증명을 제공하는 서비스 계층의 계약을 정의합니다.
 * 
 * 주요 역할:
 * - STS(Security Token Service) 임시 자격 증명 생성 및 제공
 * - 클라우드 스토리지 접근을 위한 보안 토큰 관리
 * - 미디어 파일 및 웨이라인 파일 업로드 지원
 * 
 * 구현 클래스는 실제 클라우드 스토리지 서비스(AWS S3, OSS 등)와 연동하여
 * 안전한 파일 업로드 환경을 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/29
 */
public interface IStorageService {

    /**
     * 미디어 파일과 웨이라인 업로드를 위한 임시 자격 증명을 생성합니다.
     * 
     * 이 메서드는 클라우드 스토리지 서비스에 안전하게 접근할 수 있는
     * 임시 보안 자격 증명을 생성하고 반환합니다.
     * 
     * 반환되는 자격 증명 정보:
     * - endpoint: 스토리지 서비스 엔드포인트 URL
     * - bucket: 파일이 저장될 버킷 이름
     * - credentials: 임시 액세스 키, 시크릿 키, 세션 토큰
     * - provider: 스토리지 서비스 제공자 (AWS, Alibaba Cloud 등)
     * - objectKeyPrefix: 업로드될 파일의 키 접두사
     * - region: 스토리지 서비스 리전
     * 
     * @return 임시 자격 증명 정보를 포함한 응답 객체
     */
    StsCredentialsResponse getSTSCredentials();

}
