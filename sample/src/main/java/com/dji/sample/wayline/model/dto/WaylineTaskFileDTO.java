package com.dji.sample.wayline.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 작업 파일 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 웨이라인 작업 실행에 필요한 웨이라인 파일의 정보를 전송하기 위한 데이터 전송 객체입니다.
 * DJI Dock에서 웨이라인 작업을 실행하기 위해 필요한 파일 다운로드 정보와 무결성 검증 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일 다운로드 URL 제공
 * - 웨이라인 파일 무결성 검증을 위한 지문(fingerprint) 제공
 * - 파일 다운로드 및 검증 프로세스 지원
 * - 안전한 파일 전송 보장
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 시 파일 정보 전송
 * - DJI Dock에 웨이라인 파일 다운로드 지시
 * - 웨이라인 파일 무결성 검증
 * - 웨이라인 작업 실행 준비
 * 
 * 보안 고려사항:
 * - URL은 임시 접근 권한을 가진 서명된 URL이어야 함
 * - 지문은 파일 내용의 해시값으로 무결성 검증에 사용
 * - 파일 다운로드 후 지문 검증을 통해 파일 변조 여부 확인
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineTaskFileDTO {

    /**
     * 웨이라인 파일 다운로드 URL
     * DJI Dock이 웨이라인 파일을 다운로드할 수 있는 URL
     * 
     * URL 특징:
     * - 임시 접근 권한을 가진 서명된 URL (Presigned URL)
     * - HTTPS 프로토콜 사용으로 보안 통신 보장
     * - 제한된 유효 기간을 가짐 (보통 1시간-24시간)
     * - 특정 IP 또는 사용자만 접근 가능하도록 제한 가능
     * 
     * 사용 과정:
     * 1. DJI Dock이 이 URL을 통해 웨이라인 파일 다운로드
     * 2. 다운로드 완료 후 fingerprint와 비교하여 무결성 검증
     * 3. 검증 성공 시 웨이라인 작업 실행 준비 완료
     */
    private String url;

    /**
     * 웨이라인 파일 지문 (Fingerprint)
     * 웨이라인 파일의 무결성을 검증하기 위한 해시값
     * 
     * 지문 특징:
     * - 파일 내용의 SHA-256 해시값 (일반적으로)
     * - 파일이 변조되지 않았는지 확인하는 용도
     * - 다운로드된 파일과 원본 파일의 일치성 검증
     * 
     * 검증 과정:
     * 1. DJI Dock이 파일 다운로드 완료
     * 2. 다운로드된 파일의 해시값 계산
     * 3. 계산된 해시값과 이 fingerprint 비교
     * 4. 일치하면 파일 무결성 확인, 불일치하면 오류 처리
     */
    private String fingerprint;
}
