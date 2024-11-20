package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.NoDuplicationMission;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MemberMissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{memberId}/{missionId}")
    public ApiResponse<MemberMissionResponseDTO> createMemberMission(
            @PathVariable("memberId") Long memberId,
            @PathVariable("missionId") @Valid @NoDuplicationMission Long missionId){
        MemberMissionResponseDTO responseDTO = missionCommandService.register (memberId, missionId);

        return ApiResponse.onSuccess(responseDTO);
    }
}
