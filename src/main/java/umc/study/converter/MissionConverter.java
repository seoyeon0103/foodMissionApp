package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

public class MissionConverter {
    public static MissionResponseDto.MissionResponse tomissionResponse(Mission mission){
        return MissionResponseDto.MissionResponse
                .builder()
                .missionId(mission.getId())
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
