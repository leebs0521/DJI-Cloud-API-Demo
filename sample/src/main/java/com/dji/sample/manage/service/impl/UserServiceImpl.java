package com.dji.sample.manage.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.common.util.JwtUtil;
import com.dji.sample.component.mqtt.config.MqttPropertyConfiguration;
import com.dji.sample.manage.dao.IUserMapper;
import com.dji.sample.manage.model.dto.UserDTO;
import com.dji.sample.manage.model.dto.UserListDTO;
import com.dji.sample.manage.model.dto.WorkspaceDTO;
import com.dji.sample.manage.model.entity.UserEntity;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.IUserService;
import com.dji.sample.manage.service.IWorkspaceService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자 관리 서비스 구현체
 * 
 * DJI Cloud API의 사용자 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 사용자 인증 및 로그인 관리
 *    - 사용자명과 비밀번호 기반 로그인 인증
 *    - JWT 토큰 기반 인증 및 갱신
 *    - 사용자 세션 관리 및 보안 검증
 *    - 사용자 타입별 인증 플래그 검증
 * 
 * 2. 사용자 정보 관리
 *    - 사용자 상세 정보 조회 및 검증
 *    - 워크스페이스별 사용자 목록 관리
 *    - 사용자 정보 업데이트 및 수정
 *    - 사용자 권한 및 역할 검증
 * 
 * 3. 워크스페이스 사용자 관리
 *    - 워크스페이스 내 사용자 목록 조회
 *    - 페이지네이션을 통한 사용자 목록 관리
 *    - 워크스페이스 유효성 검증
 *    - 사용자-워크스페이스 연결 관리
 * 
 * 4. 보안 및 인증 관리
 *    - JWT 토큰 생성 및 검증
 *    - 토큰 갱신 및 만료 처리
 *    - 사용자 타입별 인증 플래그 검증
 *    - 워크스페이스 유효성 검증
 * 
 * 5. 데이터 변환 및 매핑
 *    - Entity와 DTO 간 변환
 *    - 사용자 정보 정규화
 *    - MQTT 주소 정보 설정
 *    - 사용자 메타데이터 관리
 * 
 * 주요 의존성:
 * - IUserMapper: 사용자 데이터베이스 접근
 * - MqttPropertyConfiguration: MQTT 설정 정보
 * - IWorkspaceService: 워크스페이스 관리
 * - JwtUtil: JWT 토큰 관리
 * 
 * 이 클래스는 DJI Cloud API의 사용자 계정과 권한을
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/10
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    /**
     * 사용자 데이터베이스 매퍼
     * 사용자 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IUserMapper mapper;

    /**
     * MQTT 속성 설정
     * MQTT 연결 및 설정 정보를 관리
     */
    @Autowired
    private MqttPropertyConfiguration mqttPropertyConfiguration;

    /**
     * 워크스페이스 관리 서비스
     * 조직 및 워크스페이스 정보를 관리
     */
    @Autowired
    private IWorkspaceService workspaceService;

    /**
     * 사용자명으로 사용자 정보를 조회합니다.
     * 
     * @param username 사용자명
     * @param workspaceId 워크스페이스 ID
     * @return 사용자 정보가 포함된 HTTP 응답
     */
    @Override
    public HttpResultResponse getUserByUsername(String username, String workspaceId) {

        UserEntity userEntity = this.getUserByUsername(username);
        if (userEntity == null) {
            return new HttpResultResponse()
                    .setCode(HttpStatus.UNAUTHORIZED.value())
                    .setMessage("invalid username");
        }

        UserDTO user = this.entityConvertToDTO(userEntity);
        user.setWorkspaceId(workspaceId);

        return HttpResultResponse.success(user);
    }

    /**
     * 사용자 로그인을 처리합니다.
     * 
     * 사용자명, 비밀번호, 사용자 타입을 검증하고 JWT 토큰을 생성합니다.
     * 
     * @param username 사용자명
     * @param password 비밀번호
     * @param flag 사용자 타입 플래그 (PILOT 또는 WEB)
     * @return 로그인 성공 시 사용자 정보와 토큰, 실패 시 오류 메시지
     */
    @Override
    public HttpResultResponse userLogin(String username, String password, Integer flag) {
        // 사용자 존재 여부 확인
        UserEntity userEntity = this.getUserByUsername(username);
        if (userEntity == null) {
            return new HttpResultResponse()
                    .setCode(HttpStatus.UNAUTHORIZED.value())
                    .setMessage("invalid username");
        }
        // 사용자 타입 검증
        if (flag.intValue() != userEntity.getUserType().intValue()) {
            return HttpResultResponse.error("The account type does not match.");
        }
        // 비밀번호 검증
        if (!password.equals(userEntity.getPassword())) {
            return new HttpResultResponse()
                    .setCode(HttpStatus.UNAUTHORIZED.value())
                    .setMessage("invalid password");
        }

        // 워크스페이스 유효성 검증
        Optional<WorkspaceDTO> workspaceOpt = workspaceService.getWorkspaceByWorkspaceId(userEntity.getWorkspaceId());
        if (workspaceOpt.isEmpty()) {
            return new HttpResultResponse()
                    .setCode(HttpStatus.UNAUTHORIZED.value())
                    .setMessage("invalid workspace id");
        }

        // JWT 토큰 생성을 위한 클레임 정보 생성
        CustomClaim customClaim = new CustomClaim(userEntity.getUserId(),
                userEntity.getUsername(), userEntity.getUserType(),
                workspaceOpt.get().getWorkspaceId());

        // JWT 토큰 생성
        String token = JwtUtil.createToken(customClaim.convertToMap());

        // 응답 데이터 구성
        UserDTO userDTO = entityConvertToDTO(userEntity);
        userDTO.setMqttAddr(MqttPropertyConfiguration.getBasicMqttAddress());
        userDTO.setAccessToken(token);
        userDTO.setWorkspaceId(workspaceOpt.get().getWorkspaceId());
        return HttpResultResponse.success(userDTO);
    }

    /**
     * JWT 토큰을 갱신합니다.
     * 
     * 기존 토큰을 검증하고 새로운 토큰을 생성합니다.
     * 토큰이 만료된 경우에도 갱신을 시도합니다.
     * 
     * @param token 갱신할 JWT 토큰
     * @return 갱신된 사용자 정보 (Optional)
     */
    @Override
    public Optional<UserDTO> refreshToken(String token) {
        if (!StringUtils.hasText(token)) {
            return Optional.empty();
        }
        CustomClaim customClaim;
        try {
            // 토큰 검증 및 디코딩
            DecodedJWT jwt = JwtUtil.verifyToken(token);
            customClaim = new CustomClaim(jwt.getClaims());
        } catch (TokenExpiredException e) {
            // 토큰이 만료된 경우에도 클레임 정보 추출
            customClaim = new CustomClaim(JWT.decode(token).getClaims());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        // 새로운 토큰 생성
        String refreshToken = JwtUtil.createToken(customClaim.convertToMap());

        // 사용자 정보 조회 및 응답 구성
        UserDTO user = entityConvertToDTO(this.getUserByUsername(customClaim.getUsername()));
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        user.setWorkspaceId(customClaim.getWorkspaceId());
        user.setAccessToken(refreshToken);
        return Optional.of(user);
    }

    /**
     * 워크스페이스별 사용자 목록을 페이지네이션으로 조회합니다.
     * 
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @param workspaceId 워크스페이스 ID
     * @return 페이지네이션된 사용자 목록
     */
    @Override
    public PaginationData<UserListDTO> getUsersByWorkspaceId(long page, long pageSize, String workspaceId) {
        Page<UserEntity> userEntityPage = mapper.selectPage(
                new Page<>(page, pageSize),
                new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getWorkspaceId, workspaceId));

        List<UserListDTO> usersList = userEntityPage.getRecords()
                .stream()
                .map(this::entity2UserListDTO)
                .collect(Collectors.toList());
        return new PaginationData<>(usersList, new Pagination(userEntityPage.getCurrent(), userEntityPage.getSize(), userEntityPage.getTotal()));
    }

    /**
     * 사용자 정보를 업데이트합니다.
     * 
     * MQTT 사용자명과 비밀번호를 업데이트합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userId 사용자 ID
     * @param user 업데이트할 사용자 정보
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean updateUser(String workspaceId, String userId, UserListDTO user) {
        UserEntity userEntity = mapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUserId, userId)
                        .eq(UserEntity::getWorkspaceId, workspaceId));
        if (userEntity == null) {
            return false;
        }
        userEntity.setMqttUsername(user.getMqttUsername());
        userEntity.setMqttPassword(user.getMqttPassword());
        userEntity.setUpdateTime(System.currentTimeMillis());
        int id = mapper.update(userEntity, new LambdaUpdateWrapper<UserEntity>()
                .eq(UserEntity::getUserId, userId)
                .eq(UserEntity::getWorkspaceId, workspaceId));

        return id > 0;
    }

    /**
     * 데이터베이스 엔티티 객체를 사용자 목록 DTO로 변환합니다.
     * 
     * @param entity 사용자 엔티티
     * @return 사용자 목록 DTO
     */
    private UserListDTO entity2UserListDTO(UserEntity entity) {
        UserListDTO.UserListDTOBuilder builder = UserListDTO.builder();
        if (entity != null) {
            builder.userId(entity.getUserId())
                    .username(entity.getUsername())
                    .mqttUsername(entity.getMqttUsername())
                    .mqttPassword(entity.getMqttPassword())
                    .userType(UserTypeEnum.find(entity.getUserType()).getDesc())
                    .createTime(LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(entity.getCreateTime()), ZoneId.systemDefault()));
            Optional<WorkspaceDTO> workspaceOpt = workspaceService.getWorkspaceByWorkspaceId(entity.getWorkspaceId());
            workspaceOpt.ifPresent(workspace -> builder.workspaceName(workspace.getWorkspaceName()));
        }

        return builder.build();
    }

    /**
     * 사용자명으로 사용자를 조회합니다.
     * 
     * @param username 사용자명
     * @return 사용자 엔티티
     */
    private UserEntity getUserByUsername(String username) {
        return mapper.selectOne(new QueryWrapper<UserEntity>()
                .eq("username", username));
    }

    /**
     * 데이터베이스 엔티티 객체를 사용자 DTO로 변환합니다.
     * 
     * @param entity 사용자 엔티티
     * @return 사용자 DTO
     */
    private UserDTO entityConvertToDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return UserDTO.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .userType(entity.getUserType())
                .mqttUsername(entity.getMqttUsername())
                .mqttPassword(entity.getMqttPassword())
                .mqttAddr(MqttPropertyConfiguration.getBasicMqttAddress())
                .build();
    }
}
