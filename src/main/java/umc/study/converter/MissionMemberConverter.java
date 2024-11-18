package umc.study.converter;


import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

public class MissionMemberConverter {
    public static MemberMissionResponseDTO toMemberMission(MemberMission memberMission){
        return MemberMissionResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .foodCategory(memberMission.getMission().getStore().getFoodCategory().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .build();
    }
}
