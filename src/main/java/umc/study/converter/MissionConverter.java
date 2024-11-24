package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDto.MissionResponse tomissionResponse(Mission mission, String storename, String foodcategory){
        return MissionResponseDto.MissionResponse
                .builder()
                .storename(storename)
                .foodKind(foodcategory)
                .content(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }

    public static Mission toMission(MissionRequestDto.MissionRequest request){
        return Mission.builder()
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDto.MissionResponse tostoreMission(Mission mission){
        return MissionResponseDto.MissionResponse
                .builder()
                .storename(mission.getStore().getName())
                .foodKind(mission.getStore().getFoodCategory().getName())
                .content(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }


    public static MissionResponseDto.storeMissionResponse toStoreMissionList(
            Page<Mission> missionList){
        List<MissionResponseDto.MissionResponse> missionDetailList =
                missionList.stream()
                        .map(MissionConverter::tostoreMission)
                        .collect(Collectors.toList());

        return MissionResponseDto.storeMissionResponse.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionDetailList.size())
                .storeMissionList(missionDetailList)
                .build();
    }
}
