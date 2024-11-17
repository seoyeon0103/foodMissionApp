package umc.study.converter;

import umc.study.domain.FoodCategory;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

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
}
