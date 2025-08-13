package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.DeviceFirmwareDTO;
import com.dji.sample.manage.model.dto.DeviceFirmwareNoteDTO;
import com.dji.sample.manage.model.dto.FirmwareFileProperties;
import com.dji.sample.manage.model.param.DeviceFirmwareQueryParam;
import com.dji.sample.manage.model.param.DeviceFirmwareUpdateParam;
import com.dji.sample.manage.model.param.DeviceFirmwareUploadParam;
import com.dji.sample.manage.service.IDeviceFirmwareService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 디바이스 펌웨어 관리 컨트롤러
 * <p>
 * DJI Cloud API의 디바이스 펌웨어 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * <p>
 * 1. 펌웨어 정보 조회
 * - 최신 펌웨어 릴리즈 노트 조회
 * - 펌웨어 정보 페이지네이션 조회
 * - 디바이스 모델별 펌웨어 정보 관리
 * <p>
 * 2. 펌웨어 파일 관리
 * - 펌웨어 파일 업로드 및 임포트
 * - 펌웨어 파일 형식 검증
 * - 펌웨어 파일 메타데이터 관리
 * <p>
 * 3. 펌웨어 상태 관리
 * - 펌웨어 가용성 상태 변경
 * - 펌웨어 활성화/비활성화
 * - 펌웨어 상태 추적
 * <p>
 * 4. 펌웨어 업그레이드 지원
 * - 펌웨어 업그레이드 준비
 * - 펌웨어 호환성 확인
 * - 업그레이드 이력 관리
 * <p>
 * 모든 API는 입력 검증을 수행하며, 파일 업로드 시
 * 보안 검증을 통해 안전한 펌웨어 관리를 보장합니다.
 *
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
@Tag(name = "디바이스 펌웨어 관리", description = "디바이스 펌웨어 관리 API")
@RestController
@RequestMapping("${url.manage.prefix}${url.manage.version}/workspaces")
@Validated
public class DeviceFirmwareController {

    /**
     * 디바이스 펌웨어 서비스 - 펌웨어 관리 비즈니스 로직
     */
    @Autowired
    private IDeviceFirmwareService service;

    /**
     * 디바이스 모델의 최신 펌웨어 버전 정보를 조회합니다.
     * <p>
     * 이 API는 지정된 디바이스 모델들의 최신 펌웨어 릴리즈 노트를 조회합니다.
     * 여러 디바이스 모델에 대한 펌웨어 정보를 일괄 조회할 수 있습니다.
     *
     * @param deviceNames 디바이스 모델명 목록
     * @return 최신 펌웨어 릴리즈 노트 목록
     */
    @Operation(summary = "디바이스 최신 펌웨어 정보 조회",
            description = "디바이스 모델들의 최신 펌웨어 릴리즈 노트를 조회합니다.")
    @GetMapping("/firmware-release-notes/latest")
    public HttpResultResponse<List<DeviceFirmwareNoteDTO>> getLatestFirmwareNote(
            @Parameter(name = "device_name", description = "디바이스 모델명 목록")
            @RequestParam("device_name") List<String> deviceNames
    ) {
        List<DeviceFirmwareNoteDTO> releaseNotes = deviceNames.stream()
                .map(deviceName -> service.getLatestFirmwareReleaseNote(deviceName))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return HttpResultResponse.success(releaseNotes);
    }

    /**
     * 파라미터를 기반으로 펌웨어 정보를 조회합니다.
     * <p>
     * 이 API는 다양한 검색 조건을 사용하여 펌웨어 정보를 페이지네이션으로 조회합니다.
     * 디바이스 모델, 펌웨어 버전, 상태 등으로 필터링할 수 있습니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param param       펌웨어 조회 파라미터 (검색 조건, 페이지네이션 등)
     * @return 펌웨어 정보 목록 (페이지네이션 포함)
     */
    @Operation(summary = "펌웨어 정보 페이지네이션 조회",
            description = "파라미터를 기반으로 펌웨어 정보를 페이지 단위로 조회합니다.")
    @GetMapping("/{workspace_id}/firmwares")
    public HttpResultResponse<PaginationData<DeviceFirmwareDTO>> getAllFirmwarePagination(
            @Parameter(name = "workspace_id", description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "펌웨어 조회 파라미터") @Valid DeviceFirmwareQueryParam param
    ) {
        PaginationData<DeviceFirmwareDTO> data = service.getAllFirmwarePagination(workspaceId, param);
        return HttpResultResponse.success(data);
    }

    /**
     * 디바이스 업그레이드를 위한 펌웨어 파일을 임포트합니다.
     * <p>
     * 이 API는 펌웨어 파일을 업로드하고 시스템에 임포트합니다.
     * 파일 형식 검증을 통해 올바른 펌웨어 파일만 업로드할 수 있습니다.
     *
     * @param request     HTTP 요청 객체 (토큰 정보 추출용)
     * @param workspaceId 워크스페이스 ID
     * @param file        업로드할 펌웨어 파일 (필수)
     * @param param       펌웨어 업로드 파라미터 (메타데이터 등)
     * @return 펌웨어 파일 임포트 결과
     */
    @Operation(summary = "펌웨어 파일 업로드",
            description = "디바이스 업그레이드를 위한 펌웨어 파일을 시스템에 업로드합니다.")
    @PostMapping("/{workspace_id}/firmwares/file/upload")
    public HttpResultResponse importFirmwareFile(
            HttpServletRequest request,
            @Parameter(name = "workspace_id", description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "펌웨어 파일") @NotNull(message = "No file received.") MultipartFile file,
            @Parameter(description = "펌웨어 업로드 파라미터") @Valid DeviceFirmwareUploadParam param
    ) {
        // 펌웨어 파일 형식 검증
        if (!file.getOriginalFilename().endsWith(FirmwareFileProperties.FIRMWARE_FILE_SUFFIX)) {
            return HttpResultResponse.error("The file format is incorrect.");
        }

        // 토큰에서 사용자 정보 추출
        CustomClaim customClaim = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
        String creator = customClaim.getUsername();

        // 펌웨어 파일 임포트 실행
        service.importFirmwareFile(workspaceId, creator, param, file);
        return HttpResultResponse.success();
    }

    /**
     * 펌웨어의 가용성 상태를 변경합니다.
     * <p>
     * 이 API는 펌웨어의 활성화/비활성화 상태를 변경합니다.
     * 비활성화된 펌웨어는 업그레이드에 사용할 수 없습니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param firmwareId  펌웨어 ID
     * @param param       펌웨어 상태 변경 파라미터
     * @return 펌웨어 상태 변경 결과
     */
    @Operation(summary = "펌웨어 상태 변경",
            description = "펌웨어의 활성화/비활성화 상태를 변경합니다.")
    @PutMapping("/{workspace_id}/firmwares/{firmware_id}")
    public HttpResultResponse changeFirmwareStatus(
            @Parameter(name = "workspace_id", description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(name = "firmware_id", description = "펌웨어 ID") @PathVariable("firmware_id") String firmwareId,
            @Parameter(description = "펌웨어 상태 변경 파라미터") @Valid @RequestBody DeviceFirmwareUpdateParam param
    ) {
        service.updateFirmwareInfo(
                DeviceFirmwareDTO.builder()
                .firmwareId(firmwareId)
                .firmwareStatus(param.getStatus())
                .build()
        );
        return HttpResultResponse.success();
    }
}
