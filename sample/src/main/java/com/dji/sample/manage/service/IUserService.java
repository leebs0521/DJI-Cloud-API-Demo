package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.UserDTO;
import com.dji.sample.manage.model.dto.UserListDTO;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;

import java.util.Optional;

/**
 * 사용자 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 사용자 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 인증 및 로그인 관리
 *    - 사용자명과 비밀번호 기반 로그인 인증
 *    - 토큰 기반 인증 및 갱신
 *    - 사용자 세션 관리
 * 
 * 2. 사용자 정보 관리
 *    - 사용자 상세 정보 조회
 *    - 워크스페이스별 사용자 목록 관리
 *    - 사용자 정보 업데이트
 * 
 * 3. 워크스페이스 사용자 관리
 *    - 워크스페이스 내 사용자 목록 조회
 *    - 페이지네이션을 통한 사용자 목록 관리
 *    - 사용자 권한 및 역할 관리
 * 
 * 4. 보안 및 인증 관리
 *    - 사용자 인증 토큰 관리
 *    - 토큰 갱신 및 만료 처리
 *    - 보안 플래그 기반 인증
 * 
 * 이 인터페이스는 DJI Cloud API의 사용자 계정과 권한을
 * 체계적으로 관리하고 인증할 수 있도록 지원합니다.
 */
public interface IUserService {

    /**
     * 사용자명으로 사용자 상세 정보 조회
     * 
     * 사용자명과 워크스페이스 ID를 기반으로
     * 해당 사용자의 상세 정보를 조회합니다.
     * 
     * @param username 사용자명
     * @param workspaceId 워크스페이스 ID
     * @return 사용자 상세 정보 (HTTP 응답 형태)
     */
    HttpResultResponse getUserByUsername(String username, String workspaceId);

    /**
     * 사용자 로그인 인증
     * 
     * 사용자명과 비밀번호를 기반으로 로그인 인증을 수행합니다.
     * 보안 플래그를 통해 추가적인 인증 요구사항을 처리할 수 있습니다.
     * 
     * @param username 사용자명
     * @param password 비밀번호
     * @param flag 보안 플래그 (추가 인증 요구사항)
     * @return 로그인 인증 결과 (HTTP 응답 형태)
     */
    HttpResultResponse userLogin(String username, String password, Integer flag);

    /**
     * 토큰 갱신
     * 
     * 기존 토큰을 기반으로 새로운 토큰을 생성하여
     * 사용자 객체를 갱신합니다.
     * 
     * @param token 갱신할 토큰
     * @return 갱신된 사용자 정보 (Optional)
     */
    Optional<UserDTO> refreshToken(String token);

    /**
     * 워크스페이스별 사용자 목록 조회
     * 
     * 특정 워크스페이스 내의 모든 사용자 정보를
     * 페이지네이션과 함께 조회합니다.
     * 
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @param workspaceId 워크스페이스 ID (UUID)
     * @return 페이지네이션된 사용자 목록
     */
    PaginationData<UserListDTO> getUsersByWorkspaceId(long page, long pageSize, String workspaceId);

    /**
     * 사용자 정보 업데이트
     * 
     * 특정 워크스페이스의 사용자 정보를 업데이트합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userId 사용자 ID
     * @param user 업데이트할 사용자 정보
     * @return 업데이트 성공 여부
     */
    Boolean updateUser(String workspaceId, String userId, UserListDTO user);
}
