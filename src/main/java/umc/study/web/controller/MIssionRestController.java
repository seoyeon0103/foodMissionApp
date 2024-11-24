package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.exception.PageValidationException;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MIssionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/create")
    public ApiResponse<MissionResponseDto.MissionResponse> createMission(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionRequestDto.MissionRequest request) {
       MissionResponseDto.MissionResponse response = missionCommandService.create(storeId, request);

       return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{storeId}/store")
    public ApiResponse<MissionResponseDto.storeMissionResponse> storeMissionList(
            @ExistStore @PathVariable("storeId") Long storeId,
            @RequestParam(name = "page") Integer page){
        Integer pageCustom;

        if(page > 0){
            pageCustom = page - 1;
        }else{
            throw new PageValidationException("Page Number must be greater than 0");
        }

        Page<Mission> storeMission = missionCommandService.getStoreMissions(storeId, pageCustom);

        return ApiResponse.onSuccess(MissionConverter.toStoreMissionList(storeMission));
    }
}
