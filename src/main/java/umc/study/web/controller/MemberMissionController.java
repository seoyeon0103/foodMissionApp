package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.exception.PageValidationException;
import umc.study.converter.MissionConverter;
import umc.study.converter.MissionMemberConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.NoDuplicationMission;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

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

    @GetMapping("/{memberId}")
    public ApiResponse<MissionResponseDto.myMissionListViewResponse> showMemberMission(
            @ExistMember @PathVariable("memberId") Long memberId,
            @RequestParam(name = "page") Integer page){
        Integer pageCustom;

        if(page >0){
            pageCustom = page - 1;
        } else {
            throw new PageValidationException("Page number must be greater than 0");
        }

        Page<MemberMission> myChallengingMission = missionCommandService.getMyChallengingMissions(memberId, pageCustom);

        return ApiResponse.onSuccess(MissionMemberConverter.toMyMissionList(myChallengingMission));
    }

}
