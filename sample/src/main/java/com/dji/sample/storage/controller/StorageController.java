package com.dji.sample.storage.controller;

import com.dji.sample.storage.service.IStorageService;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.cloudapi.storage.api.IHttpStorageService;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DJI Cloud API 스토리지 관련 컨트롤러
 * 
 * 이 클래스는 DJI Pilot에서 미디어 파일과 웨이라인을 업로드하기 위한
 * 임시 자격 증명을 제공하는 REST API 엔드포인트를 구현합니다.
 * 
 * 주요 기능:
 * - STS(Security Token Service) 임시 자격 증명 발급
 * - 미디어 파일 업로드를 위한 보안 토큰 제공
 * - 웨이라인 파일 업로드를 위한 보안 토큰 제공
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/29
 */
@RestController
public class StorageController implements IHttpStorageService {

    /**
     * 스토리지 서비스 인터페이스
     * 실제 스토리지 관련 비즈니스 로직을 처리하는 서비스 계층
     */
    @Autowired
    private IStorageService storageService;

    /**
     * DJI Pilot에서 미디어 파일과 웨이라인을 업로드하기 위한 임시 자격 증명을 제공합니다.
     * 
     * 이 메서드는 AWS STS(Security Token Service)를 사용하여 임시 보안 자격 증명을 생성합니다.
     * 생성된 자격 증명은 DJI Pilot 앱에서 안전하게 파일을 클라우드 스토리지에 업로드할 수 있도록 합니다.
     * 
     * 임시 자격 증명의 장점:
     * - 보안성: 영구적인 액세스 키 대신 임시 토큰 사용
     * - 제한된 권한: 필요한 최소 권한만 부여
     * - 자동 만료: 설정된 시간 후 자동으로 만료되어 보안 강화
     * 
     * @param workspaceId 워크스페이스 ID (현재 구현에서는 사용되지 않음)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 임시 자격 증명 정보를 포함한 HTTP 응답
     *         - accessKeyId: 임시 액세스 키 ID
     *         - secretAccessKey: 임시 시크릿 액세스 키
     *         - sessionToken: 임시 세션 토큰
     *         - expiration: 자격 증명 만료 시간
     */
    @Override
    public HttpResultResponse<StsCredentialsResponse> getTemporaryCredential(String workspaceId, HttpServletRequest req, HttpServletResponse rsp) {
        // 스토리지 서비스를 통해 STS 임시 자격 증명을 생성
        StsCredentialsResponse stsCredentials = storageService.getSTSCredentials();
        
        // 성공 응답으로 임시 자격 증명 정보를 반환
        return HttpResultResponse.success(stsCredentials);
    }
}
