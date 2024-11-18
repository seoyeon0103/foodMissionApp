package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MIssionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/create")
    public ApiResponse<MissionResponseDto.MissionResponse> createMission(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionRequestDto.MissionRequest request) {
       MissionResponseDto.MissionResponse response = missionCommandService.create(storeId, request);

       return ApiResponse.onSuccess(response);
    }
}
